package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.UserApplication;
import br.com.impacta.moedinhas.application.adapter.UserAdapter;
import br.com.impacta.moedinhas.application.dto.request.UserRequest;
import br.com.impacta.moedinhas.application.dto.response.UserResponse;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserApplicationImpl implements UserApplication {

    private final UserService userService;

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = userService.create(UserAdapter.toEntity(userRequest));
        return UserAdapter.toResponse(user);
    }
}
