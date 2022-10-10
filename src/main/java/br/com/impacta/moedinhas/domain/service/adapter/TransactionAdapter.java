package br.com.impacta.moedinhas.domain.service.adapter;

import br.com.impacta.moedinhas.domain.model.Account;
import br.com.impacta.moedinhas.domain.model.Goal;
import br.com.impacta.moedinhas.domain.model.Transaction;
import br.com.impacta.moedinhas.domain.model.enums.TransactionType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionAdapter {

    public static Transaction toDepositTransaction(final Account account, final Double amount) {
        return Transaction.builder()
                .account(account)
                .createdAt(LocalDateTime.now())
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .build();
    }

    public static Transaction toWithdrawTransaction(final Account account, final Double amount, final Goal goal) {
        return Transaction.builder()
                .account(account)
                .createdAt(LocalDateTime.now())
                .amount(amount)
                .type(TransactionType.WITHDRAW)
                .goal(goal)
                .build();
    }
}
