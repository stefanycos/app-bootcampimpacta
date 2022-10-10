package br.com.impacta.moedinhas.application.dto.response;

import br.com.impacta.moedinhas.domain.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Setter
@Getter
public class AuthenticationResponse {

    private UUID id;
    private String token;

    @JsonProperty("user_type")
    private Role role;

}
