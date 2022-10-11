package br.com.impacta.moedinhas.domain.model;

import br.com.impacta.moedinhas.domain.model.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class Token {

    private UUID id;

    private String token;

    private Role role;
}
