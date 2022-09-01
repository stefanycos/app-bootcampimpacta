package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.exception.ConflictException;
import br.com.impacta.moedinhas.domain.exception.NotFoundException;
import br.com.impacta.moedinhas.domain.model.Goal;
import br.com.impacta.moedinhas.domain.service.GoalService;
import br.com.impacta.moedinhas.domain.service.adapter.ObjectBeanAdapter;
import br.com.impacta.moedinhas.infrastructure.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

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
        return goalRepository.save(goal);
    }

    @Override
    public boolean exists(Goal goal) {
        return goalRepository.findByNameIgnoreCase(goal.getName()).isPresent();
    }

    @Override
    public List<Goal> findAll() {
        Iterable<Goal> categories = goalRepository.findAll();
        return StreamSupport.stream(categories.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Goal> findAllNotReached() {
        return goalRepository.findByReachedFalse();
    }

    @Override
    public Goal update(UUID id, Goal source) {
        Goal target = this.findById(id);
        ObjectBeanAdapter.copyNonNullProperties(source, target);
        target.setUpdatedAt(LocalDateTime.now());

        return target;
    }
}
