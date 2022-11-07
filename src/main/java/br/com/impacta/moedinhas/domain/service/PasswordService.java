package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.User;

public interface PasswordService {

    User findByEmail(String email);

    User findByResetToken(String resetToken);

    void updatePassword(User user, String password);

    void createUserToken(User user);
}
