package br.com.impacta.moedinhas.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AuthenticationRequest {

    @Email
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "email", example = "joao@email.com", required = true) 
    private String email;

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "senha", example = "senha123", required = true)
    private String password;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }
}
