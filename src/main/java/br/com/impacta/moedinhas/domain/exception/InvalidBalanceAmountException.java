package br.com.impacta.moedinhas.domain.exception;

public class InvalidBalanceAmountException extends BadRequestException {

    public InvalidBalanceAmountException(String message) {
        super(message);
    }
}
