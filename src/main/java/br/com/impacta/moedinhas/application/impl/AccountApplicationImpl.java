package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.AccountApplication;
import br.com.impacta.moedinhas.application.dto.response.AccountResponse;
import br.com.impacta.moedinhas.domain.model.Account;
import br.com.impacta.moedinhas.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountApplicationImpl implements AccountApplication {

    private final AccountService accountService;

    @Override
    public void deposit(Double amount, UUID accountId) {
        accountService.deposit(amount, accountId);
    }

    @Override
    public AccountResponse getAccount(UUID userId) {
        Account account = accountService.findAccountByUserId(userId);
        return new AccountResponse(account.getId(), account.getBalance());
    }
}
