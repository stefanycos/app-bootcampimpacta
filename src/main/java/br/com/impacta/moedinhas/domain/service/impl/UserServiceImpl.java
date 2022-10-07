package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.exception.BadRequestException;
import br.com.impacta.moedinhas.domain.exception.ConflictException;
import br.com.impacta.moedinhas.domain.exception.NotFoundException;
import br.com.impacta.moedinhas.domain.model.Account;
import br.com.impacta.moedinhas.domain.model.Role;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.service.AccountService;
import br.com.impacta.moedinhas.domain.service.UserService;
import br.com.impacta.moedinhas.domain.service.adapter.ObjectBeanAdapter;
import br.com.impacta.moedinhas.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AccountService accountService;

    @Override
    public User findById(UUID id) {
        log.info("Searching user with id {}", id);
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    @Transactional
    @Override
    public User create(User user) {
        log.info("Saving user {} in database", user.getEmail());

        if (this.exists(user)) {
            throw new ConflictException(format("User with email %s already exists", user.getEmail()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        accountService.create(new Account(user, 0d));
        return user;
    }

    @Override
    public boolean exists(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }

    @Override
    public void delete(UUID id) {

        userRepository.deleteById(id);
    }

    @Override
    public User update(UUID id, User user) {
        log.info("Updating user {} in database", user.getEmail());
        User target = this.findById(id);
        ObjectBeanAdapter.copyNonNullProperties(user, target);
        target.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(target);
    }

    @Override
    public void defineResponsible(UUID idDependent, String responsibleEmail) {
        User responsible = this.getParent(responsibleEmail);

        User dependent = this.findById(idDependent);

        log.info("Setting dependent {} with responsible {}", dependent.getEmail(), responsibleEmail);

        if (!responsible.getRole().equals(Role.RESPONSIBLE))
            throw new BadRequestException(format("Parent with email %s must be type %s", responsibleEmail, Role.RESPONSIBLE));

        this.saveParent(responsible, dependent);

        log.info("Responsible defined successfully.");
    }

    @Override
    public void defineDependent(String dependentEmail, UUID idResponsible) {
        User dependent = this.getParent(dependentEmail);
        User responsible = this.findById(idResponsible);

        log.info("Setting dependent {} with responsible {}", dependent.getEmail(), responsible.getEmail());

        if (!dependent.getRole().equals(Role.CHILDREN))
            throw new BadRequestException(format("Parent with email %s must be type %s", dependentEmail, Role.CHILDREN));

        this.saveParent(dependent, responsible);

        log.info("Dependent defined successfully.");
    }

    private void saveParent(User dependent, User responsible) {
        responsible.setParent(dependent);
        responsible.setUpdatedAt(LocalDateTime.now());
        userRepository.save(responsible);
    }

    private User getParent(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new BadRequestException(format("Parent with email %s not registered yet", email)));
    }

}
