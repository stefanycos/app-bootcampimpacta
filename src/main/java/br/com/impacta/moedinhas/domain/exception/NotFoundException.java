package br.com.impacta.moedinhas.domain.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }

    public String getReasonPhrase() {
        return this.httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return this.getHttpStatus();
    }


}
