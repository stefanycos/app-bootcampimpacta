package br.com.impacta.moedinhas.application;

import br.com.impacta.moedinhas.application.dto.response.AuthenticationResponse;
import org.springframework.security.core.Authentication;

public interface TokenApplication {

    AuthenticationResponse createToken(final Authentication authentication);
}
