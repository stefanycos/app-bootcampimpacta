package br.com.impacta.moedinhas.application.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class CategoryRequest {

    @NotNull
    @NotEmpty
    private String name;

}
