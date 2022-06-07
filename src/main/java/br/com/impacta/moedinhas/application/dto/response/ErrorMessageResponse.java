package br.com.impacta.moedinhas.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorMessageResponse {

    private LocalDateTime timestamp;
    private String message;
    private String description;
}
