package br.com.impacta.moedinhas.domain.exception;

public class InvalidUserTypeForTransactionException extends BadRequestException{

    public InvalidUserTypeForTransactionException(String message) {
        super(message);
    }
}
