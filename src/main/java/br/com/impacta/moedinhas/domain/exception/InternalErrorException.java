package br.com.impacta.moedinhas.domain.exception;

public class InternalErrorException extends RuntimeException {

    public InternalErrorException(String message) {
        super(message);
    }
}
