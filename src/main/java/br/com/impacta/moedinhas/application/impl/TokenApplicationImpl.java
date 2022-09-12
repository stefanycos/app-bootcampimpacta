package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.TokenApplication;
import br.com.impacta.moedinhas.application.adapter.TokenAdapter;
import br.com.impacta.moedinhas.application.dto.response.AuthenticationResponse;
import br.com.impacta.moedinhas.domain.TokenService;
import br.com.impacta.moedinhas.domain.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenApplicationImpl implements TokenApplication {

    private final TokenService tokenService;

    @Override
    public AuthenticationResponse createToken(Authentication authentication) {
        Token token = tokenService.createToken(authentication);
        return TokenAdapter.toResponse(token);
    }
}
