package br.com.impacta.moedinhas.application;

import br.com.impacta.moedinhas.application.dto.request.UserRequest;
import br.com.impacta.moedinhas.application.dto.response.UserResponse;

public interface UserApplication {

    UserResponse create(UserRequest userRequest);
}
