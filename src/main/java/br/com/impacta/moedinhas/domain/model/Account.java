package br.com.impacta.moedinhas.domain.model;

import br.com.impacta.moedinhas.domain.exception.AccountWithoutBalanceException;
import br.com.impacta.moedinhas.domain.exception.InvalidBalanceAmountException;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Double balance;

    public Account(final User user, final Double balance) {
        this.user = user;
        this.balance = balance;
    }

    public void debit(final Double valueOfDebit) {
        if (this.getBalance() < valueOfDebit) {
            throw new AccountWithoutBalanceException("Balance not enough for transaction");
        }

        this.balance = this.getBalance() - valueOfDebit;
    }

    public void deposit(final Double valueOfDeposit) {
        if (valueOfDeposit <= 0) {
            throw new InvalidBalanceAmountException("Amount receive is not valid");
        }

        this.balance = this.getBalance() + valueOfDeposit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBalance() {
        return balance == null ? Double.valueOf(0) : balance;
    }
}
