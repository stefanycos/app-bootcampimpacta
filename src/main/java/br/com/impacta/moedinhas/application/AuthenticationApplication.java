package br.com.impacta.moedinhas.application;

import br.com.impacta.moedinhas.application.dto.request.AuthenticationRequest;
import br.com.impacta.moedinhas.application.dto.response.AuthenticationResponse;
import br.com.impacta.moedinhas.application.dto.response.LoggedUserResponse;

public interface AuthenticationApplication {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    LoggedUserResponse getLoggedUser();
}
