package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.Account;
import br.com.impacta.moedinhas.domain.model.Goal;

import java.util.UUID;

public interface AccountService {

    void create(Account account);

    void save(Account account);

    Account findById(UUID id);

    Account findAccountByUserId(UUID id);

    void deposit(Double amount, UUID accountId);

    void withdraw(Double amount, UUID accountId, Goal goal);
}
