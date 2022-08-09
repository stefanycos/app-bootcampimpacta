package br.com.impacta.moedinhas.application.dto.request;

import br.com.impacta.moedinhas.domain.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@NoArgsConstructor
@Data
public class UserRequest {

    @NotNull
    @NotEmpty
    private String name;

    @Null(groups = Views.OnUpdate.class)
    @NotNull
    @NotEmpty
    private String password;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @Null(groups = Views.OnUpdate.class)
    @NotNull
    @JsonProperty("user_type")
    private Role role;

    @NotNull
    @NotEmpty
    private String birthday;

}
