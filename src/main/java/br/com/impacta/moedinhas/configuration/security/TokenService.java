package br.com.impacta.moedinhas.configuration.security;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String createToken(Authentication authentication);

    boolean isValid(String token);

    String retrieveUserId(String token);
}
