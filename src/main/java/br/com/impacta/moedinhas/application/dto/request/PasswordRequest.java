package br.com.impacta.moedinhas.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class PasswordRequest {

    @NotEmpty
    @NotNull
    private String password;
}
