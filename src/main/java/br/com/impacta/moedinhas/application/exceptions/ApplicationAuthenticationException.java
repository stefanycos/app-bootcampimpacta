package br.com.impacta.moedinhas.application.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class ApplicationAuthenticationException extends AuthenticationException {

    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public ApplicationAuthenticationException(String msg) {
        super(msg);
    }

    public String getReasonPhrase() {
        return httpStatus.getReasonPhrase().concat(" Bad Credentials.");
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
