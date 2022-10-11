package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.exception.BadRequestException;
import br.com.impacta.moedinhas.domain.exception.ConflictException;
import br.com.impacta.moedinhas.domain.exception.NotFoundException;
import br.com.impacta.moedinhas.domain.model.Account;
import br.com.impacta.moedinhas.domain.model.Goal;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.model.enums.Role;
import br.com.impacta.moedinhas.domain.service.AccountService;
import br.com.impacta.moedinhas.domain.service.AuthenticationService;
import br.com.impacta.moedinhas.domain.service.GoalService;
import br.com.impacta.moedinhas.domain.service.adapter.ObjectBeanAdapter;
import br.com.impacta.moedinhas.infrastructure.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    private final AuthenticationService authenticationService;

    private final AccountService accountService;

    @Override
    public Goal findById(UUID id) {
        log.info("Searching category with id {}", id);
        return goalRepository.findById(id).orElseThrow(() -> new NotFoundException("Category Not Found"));
    }

    @Override
    public Goal save(Goal goal) {
        log.info("Saving goal {} in database", goal.getName());

        if (this.exists(goal)) {
            throw new ConflictException(format("Goal named %s already exists", goal.getName()));
        }

        goal.setReached(false);
        goal.setCreatedAt(LocalDateTime.now());
        goal.setUser(authenticationService.getLoggedUser());

        Goal savedGoal = goalRepository.save(goal);

        log.info("Goal with id {} successfully saved", savedGoal.getId());
        return savedGoal;
    }

    @Override
    public boolean exists(Goal goal) {
        return goalRepository.findByNameIgnoreCase(goal.getName()).isPresent();
    }

    @Override
    public Page<Goal> findByReachedAndUserId(Pageable pageable, Boolean reached) {
        User user = authenticationService.getLoggedUser();
        return goalRepository.findByReachedAndUserId(pageable, reached,
                user.getRole().equals(Role.CHILDREN) ? user.getId() : this.getUserParent(user).getId());
    }

    @Override
    public Goal update(UUID id, Goal source) {
        Goal target = this.findById(id);
        ObjectBeanAdapter.copyNonNullProperties(source, target);
        target.setUpdatedAt(LocalDateTime.now());
        target.setUser(authenticationService.getLoggedUser());

        goalRepository.save(target);
        return target;
    }

    @Transactional
    @Override
    public Goal approve(UUID id) {
        Goal goal = this.findById(id);

        if (goal.getReached())
            throw new BadRequestException("Goal has already been approved");

        Account account = goal.getUser().getAccount().orElseThrow(() -> new NotFoundException("Account for user not found"));
        accountService.withdraw(goal.getCost(), account.getId(), goal);

        goal.setReached(true);
        goalRepository.save(goal);

        return goal;
    }

    private User getUserParent(User user) {
        return user.getParent().orElseThrow(() -> new BadRequestException("This parent has no linked children yet."));
    }
}
