package br.com.impacta.moedinhas.domain.exception;

public class CategoryConflictException extends RuntimeException {

    public CategoryConflictException(String message) {
        super(message);
    }
}
