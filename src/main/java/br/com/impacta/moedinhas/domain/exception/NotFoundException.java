package br.com.impacta.moedinhas.domain.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }

    public String getReasonPhrase() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}
