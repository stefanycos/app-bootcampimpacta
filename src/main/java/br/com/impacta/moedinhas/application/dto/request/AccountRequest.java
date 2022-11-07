package br.com.impacta.moedinhas.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AccountRequest {

    @NotNull
    private Double amount;
}
