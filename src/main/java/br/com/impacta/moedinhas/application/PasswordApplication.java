package br.com.impacta.moedinhas.application;

public interface PasswordApplication {

    void sendResetPasswordLinkEmail(final String email);

    void changePassword(final String token, final String password);
}
