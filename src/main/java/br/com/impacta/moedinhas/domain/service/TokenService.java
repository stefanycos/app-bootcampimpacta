package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.Token;
import org.springframework.security.core.Authentication;

public interface TokenService {

    Token createToken(Authentication authentication);

    boolean isValid(String token);

    String retrieveUserId(String token);
}
