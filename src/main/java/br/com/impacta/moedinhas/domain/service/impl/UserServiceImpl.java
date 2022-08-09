package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.exception.BadRequestException;
import br.com.impacta.moedinhas.domain.exception.ConflictException;
import br.com.impacta.moedinhas.domain.exception.NotFoundException;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.service.UserService;
import br.com.impacta.moedinhas.domain.service.adapter.ObjectBeanAdapter;
import br.com.impacta.moedinhas.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(UUID id) {
        log.info("Searching user with id {}", id);
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));
    }
    @Override
    public User create(User user) {
        log.info("Saving user {} in database", user.getEmail());

        if (this.exists(user)) {
            throw new ConflictException(format("User with email %s already exists", user.getEmail()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
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
        User target = this.findById(id);
        ObjectBeanAdapter.copyNonNullProperties(user, target);
        target.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(target);
    }

    private User getParent(User user) {
        return userRepository.findByEmail(user.getParentEmail()).orElseThrow(
                () -> new BadRequestException(format("Parent with email %s not registered yet", user.getParentEmail())));
    }

}
