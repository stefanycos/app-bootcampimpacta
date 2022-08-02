package br.com.impacta.moedinhas.application.adapter;

import br.com.impacta.moedinhas.application.dto.request.UserRequest;
import br.com.impacta.moedinhas.application.dto.response.CategoryResponse;
import br.com.impacta.moedinhas.application.dto.response.UserResponse;
import br.com.impacta.moedinhas.domain.model.Category;
import br.com.impacta.moedinhas.domain.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAdapter {

    public static UserResponse toResponse(final User user) {
        return UserResponse.builder()
                .name(user.getName())
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public static User toEntity(final UserRequest request) {
        return new User(request.getName(), request.getEmail(), request.getPassword());
    }

}
