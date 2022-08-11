package br.com.impacta.moedinhas.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageResponse {

    private String message;
    private String description;
    private Map<String, Object> violations;
    private LocalDateTime timestamp;

    public void addViolation(String key, Object value) {
        if (violations == null) {
            violations = new HashMap<>();
        }
        violations.put(key, value);
    }

    public static ErrorMessageResponse build(String message, String description) {
        return ErrorMessageResponse.builder()
                .message(message)
                .description(description)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorMessageResponse build(String message, String description, Map<String, Object> violations) {
        return ErrorMessageResponse.builder()
                .message(message)
                .description(description)
                .timestamp(LocalDateTime.now())
                .violations(violations)
                .build();
    }
}
