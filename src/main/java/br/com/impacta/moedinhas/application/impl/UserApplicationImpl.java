package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.UserApplication;
import br.com.impacta.moedinhas.application.adapter.AuthenticationAdapter;
import br.com.impacta.moedinhas.application.adapter.UserAdapter;
import br.com.impacta.moedinhas.application.dto.request.UserRequest;
import br.com.impacta.moedinhas.application.dto.response.LoggedUserResponse;
import br.com.impacta.moedinhas.application.dto.response.UserResponse;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserApplicationImpl implements UserApplication {

    private final UserService userService;

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = userService.create(UserAdapter.toEntity(userRequest));
        return UserAdapter.toResponse(user);
    }

    @Override
    public UserResponse update(UUID id, UserRequest userRequest) {
        User user = userService.update(id, UserAdapter.toEntity(userRequest));
        return UserAdapter.toResponse(user);
    }

    @Override
    public LoggedUserResponse getUserParent(UUID userId) {
        User user = userService.getUserDependent(userId);
        return AuthenticationAdapter.toLoggedUserResponse(user);
    }

    @Override
    public void delete(UUID id) {
        userService.delete(id);
    }

    @Override
    public void defineResponsible(UUID idDependent, String responsibleEmail) {
        userService.defineResponsible(idDependent, responsibleEmail);
    }

    @Override
    public void defineDependent(String dependentEmail, UUID idResponsible) {
        userService.defineDependent(dependentEmail, idResponsible);
    }
}
