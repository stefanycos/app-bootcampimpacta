package br.com.impacta.moedinhas.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class DefineParentRequest {

    @Email
    @NotNull(groups = Views.OnUpdateDependent.class)
    @NotEmpty(groups = Views.OnUpdateDependent.class)
    @JsonProperty("responsible_email")
    @ApiModelProperty(notes = "email do responsável", example = "joao@email.com", required = true) 
    private String responsibleEmail;

    @Email
    @NotNull(groups = Views.OnUpdateResponsible.class)
    @NotEmpty(groups = Views.OnUpdateResponsible.class)
    @JsonProperty("dependent_email")
    @ApiModelProperty(notes = "email do dependente", example = "joao@email.com", required = true) 
    private String dependentEmail;
}
