package br.com.impacta.moedinhas.application.dto.response;

import br.com.impacta.moedinhas.domain.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class UserResponse {

    private UUID id;

    private String name;

    private String email;

    @JsonProperty("user_type")
    private Role role;

    private String birthday;

}
