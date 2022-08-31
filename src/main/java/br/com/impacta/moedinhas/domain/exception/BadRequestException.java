package br.com.impacta.moedinhas.domain.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {

    private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message);
    }

    public String getReasonPhrase() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}
