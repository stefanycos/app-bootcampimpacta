package br.com.impacta.moedinhas.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class Token {

    private UUID userId;

    private String token;

    private Role role;
}
