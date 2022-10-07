package br.com.impacta.moedinhas.domain.exception;

public class InvalidBalanceAmountException extends RuntimeException {

    public InvalidBalanceAmountException(String message) {
        super(message);
    }
}
