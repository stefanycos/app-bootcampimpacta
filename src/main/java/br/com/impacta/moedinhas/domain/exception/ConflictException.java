package br.com.impacta.moedinhas.domain.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.CONFLICT;

    public ConflictException(String message) {
        super(message);
    }

    public String getReasonPhrase() {
        return this.httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }


}
