package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.exception.NotFoundException;
import br.com.impacta.moedinhas.domain.model.Account;
import br.com.impacta.moedinhas.domain.model.Goal;
import br.com.impacta.moedinhas.domain.model.Transaction;
import br.com.impacta.moedinhas.domain.service.AccountService;
import br.com.impacta.moedinhas.domain.service.TransactionService;
import br.com.impacta.moedinhas.domain.service.adapter.TransactionAdapter;
import br.com.impacta.moedinhas.infrastructure.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final TransactionService transactionService;

    @Override
    public void create(Account account) {
        if (account.canCreateAccount())
            accountRepository.save(account);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findById(UUID id) {
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Account with id %s not found", id)));
    }

    @Override
    public Account findAccountByUserId(UUID userId) {

        return accountRepository.findAccountByUserId(userId).orElseThrow(() -> new NotFoundException("No account found, user don't have child associated yet."));
    }

    @Transactional
    @Override
    public void deposit(final Double amount, final UUID accountId) {
        log.info("Depositing amount of {} into account {}", amount, accountId);

        final Account account = this.findById(accountId);
        account.deposit(amount);
        accountRepository.save(account);

        Transaction transaction = TransactionAdapter.toDepositTransaction(account, amount);
        transactionService.save(transaction);

        log.info("Amount of {} was successfully deposited into account {}", amount, accountId);
    }

    @Transactional
    @Override
    public void withdraw(final Double amount, final UUID accountId, final Goal goal) {
        log.info("Debiting amount of {} from account {}, to goal {}", amount, accountId, goal.getName());

        final Account account = this.findById(accountId);
        account.debit(amount);
        accountRepository.save(account);

        Transaction transaction = TransactionAdapter.toWithdrawTransaction(account, amount, goal);
        transactionService.save(transaction);

        log.info("Amount of {} was successfully debited from account {} to goal {}", amount, accountId, goal.getName());
    }

}
