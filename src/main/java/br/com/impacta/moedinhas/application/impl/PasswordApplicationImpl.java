package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.PasswordApplication;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.service.EmailService;
import br.com.impacta.moedinhas.domain.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PasswordApplicationImpl implements PasswordApplication {

    private final EmailService emailService;

    private final PasswordService passwordService;

    @Transactional
    @Override
    public void sendResetPasswordLinkEmail(String email) {
        User user = passwordService.findByEmail(email);
        passwordService.createUserToken(user);
        emailService.sendEmail(user);
    }

    @Transactional
    @Override
    public void changePassword(String token, String password) {
        User user = passwordService.findByResetToken(token);
        passwordService.updatePassword(user, password);
    }
}
