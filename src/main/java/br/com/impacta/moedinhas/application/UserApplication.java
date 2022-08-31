package br.com.impacta.moedinhas.application;

import br.com.impacta.moedinhas.application.dto.request.UserRequest;
import br.com.impacta.moedinhas.application.dto.response.UserResponse;

import java.util.UUID;

public interface UserApplication {

    UserResponse create(UserRequest userRequest);

    UserResponse update(UUID id, UserRequest userRequest);

    void delete(UUID id);

    void defineResponsible(UUID idDependent, String responsibleEmail);

    void defineDependent(String dependentEmail, UUID idResponsible);
}
