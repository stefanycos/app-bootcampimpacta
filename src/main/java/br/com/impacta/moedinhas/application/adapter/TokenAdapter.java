package br.com.impacta.moedinhas.application.adapter;

import br.com.impacta.moedinhas.application.dto.response.AuthenticationResponse;
import br.com.impacta.moedinhas.domain.model.Token;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenAdapter {

    public static AuthenticationResponse toResponse(final Token token) {
        return AuthenticationResponse.builder()
                .id(token.getId())
                .token(token.getToken())
                .role(token.getRole())
                .build();
    }
}
