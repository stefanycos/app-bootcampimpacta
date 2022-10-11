package br.com.impacta.moedinhas.domain.exception;

public class AccountWithoutBalanceException extends BadRequestException {

    public AccountWithoutBalanceException(String message) {
        super(message);
    }
}
