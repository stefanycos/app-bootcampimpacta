package br.com.impacta.moedinhas.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Setter
@Getter
public class AuthenticationResponse {

    private String token;

}
