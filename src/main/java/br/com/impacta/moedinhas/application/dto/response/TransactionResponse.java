package br.com.impacta.moedinhas.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class TransactionResponse {

    private String date;

    private Double amount;

    private TransactionTypeResponse type;

    private String goal;
}
