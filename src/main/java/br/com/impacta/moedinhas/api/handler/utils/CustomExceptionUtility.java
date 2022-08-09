package br.com.impacta.moedinhas.api.handler.utils;

import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomExceptionUtility {

    private static ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public static String buildErrorBody(final RuntimeException exception) {
        try {
            return mapper.writeValueAsString(
                    ErrorMessageResponse.builder()
                            .timestamp(LocalDateTime.now())
                            .message("Forbidden")
                            .description("Access Denied").build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // TODO: replace for custom exception
        }
    }

    public static void setResponseProperties(final HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
    }
}
