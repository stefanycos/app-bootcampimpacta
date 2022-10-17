package br.com.impacta.moedinhas.application.dto.request;

import br.com.impacta.moedinhas.domain.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@ApiModel
@NoArgsConstructor
@Data
public class UserRequest {

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "nome do usuário", example = "Joao", required = true)
    private String name;

    @Null(groups = Views.OnUpdate.class)
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "senha", example = "senha123", required = true) 
    private String password;

    @Email
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "email do usuário", example = "joao@email.com", required = true) 
    private String email;

    @ApiModelProperty
    @Null(groups = Views.OnUpdate.class)
    @NotNull
    @JsonProperty("user_type")
    private Role role;

    @JsonFormat(pattern="dd/MM/yyyy")
    @ApiModelProperty(notes = "data de aniversário do usuário", example = "01/01/2000", required = true) 
    @NotNull
    @NotEmpty
    private String birthday;

}
