package br.com.impacta.moedinhas.domain.model;

import br.com.impacta.moedinhas.domain.exception.AccountWithoutBalanceException;
import br.com.impacta.moedinhas.domain.exception.InvalidBalanceAmountException;
import br.com.impacta.moedinhas.domain.exception.InvalidUserTypeForTransactionException;
import br.com.impacta.moedinhas.domain.model.enums.Role;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

        if (!this.canMakeTransactions()) {
            throw new InvalidUserTypeForTransactionException("User from type children aren't able to debit money");
        }

        this.balance = this.getBalance() - valueOfDebit;
    }

    public void deposit(final Double valueOfDeposit) {
        if (valueOfDeposit <= 0) {
            throw new InvalidBalanceAmountException("Amount receive is not valid");
        }

        if (!this.canMakeTransactions()) {
            throw new InvalidUserTypeForTransactionException("User from type children aren't able to add money");
        }

        this.balance = this.getBalance() + valueOfDeposit;
    }

    public boolean canCreateAccount() {
        return this.getUser().getRole().equals(Role.CHILDREN);
    }

    public boolean canMakeTransactions() { // TODO Fix logic, only RESPONSIBLE can make deposits
        if (!this.getUser().getParent().isPresent())
            return false;

        return this.getUser().getRole().equals(Role.CHILDREN) && this.getUser().getParent().get().getRole().equals(Role.RESPONSIBLE);
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
