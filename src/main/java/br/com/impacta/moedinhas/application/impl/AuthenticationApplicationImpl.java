package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.AuthenticationApplication;
import br.com.impacta.moedinhas.application.TokenApplication;
import br.com.impacta.moedinhas.application.adapter.AuthenticationAdapter;
import br.com.impacta.moedinhas.application.dto.request.AuthenticationRequest;
import br.com.impacta.moedinhas.application.dto.response.AuthenticationResponse;
import br.com.impacta.moedinhas.application.dto.response.LoggedUserResponse;
import br.com.impacta.moedinhas.application.exceptions.ApplicationAuthenticationException;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationApplicationImpl implements AuthenticationApplication {

    private final AuthenticationManager authManager;

    private final TokenApplication tokenApplication;

    private final AuthenticationService authenticationService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken auth = authenticationRequest.converter();
        try {
            Authentication authentication = authManager.authenticate(auth);
            return tokenApplication.createToken(authentication);
        } catch (final AuthenticationException e) {
            log.error("Error on trying to authenticate user. Error {}", e.getMessage());
            throw new ApplicationAuthenticationException("Error on trying to authenticate user.");
        }
    }

    @Override
    public LoggedUserResponse getLoggedUser() {
        User user = authenticationService.getLoggedUser();
        return AuthenticationAdapter.toResponse(user);
    }
}
