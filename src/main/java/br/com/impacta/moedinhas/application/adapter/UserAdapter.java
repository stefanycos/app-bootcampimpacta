package br.com.impacta.moedinhas.application.adapter;

import br.com.impacta.moedinhas.application.dto.request.UserRequest;
import br.com.impacta.moedinhas.application.dto.response.UserResponse;
import br.com.impacta.moedinhas.domain.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAdapter {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static UserResponse toResponse(final User user) {
        return UserResponse.builder()
                .name(user.getName())
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .birthday(user.getBirthday().format(DATE_FORMATTER))
                .build();
    }

    public static User toEntity(final UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .birthday(getBirthday(request))
                .build();
    }

    private static LocalDate getBirthday(UserRequest request) {
        return request.getBirthday() == null
                ? null :  LocalDate.parse(request.getBirthday(), DATE_FORMATTER);
    }

}
