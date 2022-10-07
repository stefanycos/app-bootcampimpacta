package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.exception.NotFoundException;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.service.PasswordService;
import br.com.impacta.moedinhas.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@AllArgsConstructor
@Service
public class PasswordServiceImpl implements PasswordService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()
                -> new NotFoundException(format("User with email %s not found", email)));
    }

    @Override
    public User findByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken).orElseThrow(()
                -> new NotFoundException(format("Reset token %s not found", resetToken)));
    }

    @Override
    public void updatePassword(User user, String password) {
        log.info("Updating password for user {}", user.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        log.info("Password successfully updated for user {}", user.getEmail());
    }

    @Override
    public void createUserToken(User user) {
        log.info("Generating new reset password token for user {}", user.getEmail());
        user.setResetToken(UUID.randomUUID().toString());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        log.info("Token generated for user {}", user.getEmail());
    }
}
