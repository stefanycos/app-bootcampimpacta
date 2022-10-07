package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.User;

public interface EmailService {

    void sendEmail(final User user);

}
