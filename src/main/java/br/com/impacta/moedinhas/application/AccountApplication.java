package br.com.impacta.moedinhas.application;

import br.com.impacta.moedinhas.application.dto.response.AccountResponse;

import java.util.UUID;

public interface AccountApplication {

    void deposit(Double amount, UUID accountId);

    AccountResponse getAccount(UUID userId);
}
