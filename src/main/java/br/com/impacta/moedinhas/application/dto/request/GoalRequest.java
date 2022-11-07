package br.com.impacta.moedinhas.application.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import io.swagger.annotations.ApiModelProperty;

import static br.com.impacta.moedinhas.application.dto.request.Views.*;

@NoArgsConstructor
@Data
public class GoalRequest {

    @NotNull(groups = OnCreate.class)
    @NotEmpty(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    @ApiModelProperty(notes = "nome do objetivo", example = "nome do objetivo", required = true) 
    private String name;

    @NotNull(groups = OnCreate.class)
    @NotEmpty(groups = OnCreate.class)
    @ApiModelProperty(notes = "descrição do objetivo", example = "descrição do objetivo", required = true)
    private String description;

    @NotNull(groups = OnCreate.class)
    private Double cost;

}
