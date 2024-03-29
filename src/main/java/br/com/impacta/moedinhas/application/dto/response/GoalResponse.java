package br.com.impacta.moedinhas.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class GoalResponse {

    private UUID id;

    private String name;

    private Boolean reached;

    private String description;

    private Double cost;

}
