package br.com.impacta.moedinhas.domain.exception;

public class AccountWithoutBalanceException extends RuntimeException {

    public AccountWithoutBalanceException(String message) {
        super(message);
    }
}
