package br.com.impacta.moedinhas.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorMessageResponse {

    private String message;
    private String description;
    private LocalDateTime timestamp;
}
