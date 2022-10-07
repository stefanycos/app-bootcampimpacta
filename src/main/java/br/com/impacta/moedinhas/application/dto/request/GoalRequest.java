package br.com.impacta.moedinhas.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@NoArgsConstructor
@Data
public class GoalRequest {

    @NotNull(groups = Views.OnCreate.class)
    @NotEmpty(groups = Views.OnCreate.class)
    @Null(groups = Views.OnUpdate.class)
    private String name;

    @NotNull(groups = Views.OnCreate.class)
    @NotEmpty(groups = Views.OnCreate.class)
    private String description;

    @NotNull(groups = Views.OnCreate.class)
    private Double cost;

    @Null(groups = Views.OnCreate.class)
    private Boolean reached;

}
