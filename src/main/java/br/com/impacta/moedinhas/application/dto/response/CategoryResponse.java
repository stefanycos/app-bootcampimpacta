package br.com.impacta.moedinhas.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CategoryResponse {

    private String id;

    private String name;

    private String status;
}
