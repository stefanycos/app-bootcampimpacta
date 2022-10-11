package br.com.impacta.moedinhas.application.adapter;

import br.com.impacta.moedinhas.application.dto.response.AccountResponse;
import br.com.impacta.moedinhas.application.dto.response.LoggedUserResponse;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.model.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationAdapter {

    public static LoggedUserResponse toResponse(User user) {
        LoggedUserResponse loggedUser = toLoggedUserResponse(user);
        loggedUser.setUserParent(user.getParent().isPresent() ? toLoggedUserResponse(user.getParent().get()) : null);

        return loggedUser;
    }

    private static LoggedUserResponse toLoggedUserResponse(User user) {
        return LoggedUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .account(user.getRole().equals(Role.CHILDREN)
                        ? new AccountResponse(user.getAccount().get().getId(), user.getAccount().get().getBalance()) : null)
                .build();
    }
}
