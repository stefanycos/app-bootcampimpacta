package br.com.impacta.moedinhas.api.handler;

import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import br.com.impacta.moedinhas.domain.exception.BadRequestException;
import br.com.impacta.moedinhas.domain.exception.ConflictException;
import br.com.impacta.moedinhas.domain.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String MESSAGE_ERROR_INTERNAL = "Erro interno!";
    private static final String DESCRIPTION_ERROR_INTERNAL = "Ocorreu um erro inesperado entre em contato como Administrador do sistema";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> categoryNotFoundException(NotFoundException exception) {
        return this.getErrorResponse(exception.getMessage(), exception.getReasonPhrase(), exception.getHttpStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessageResponse> categoryBadRequestException(BadRequestException exception) {
        return this.getErrorResponse(exception.getMessage(), exception.getReasonPhrase(), exception.getHttpStatus());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessageResponse> categoryAlreadyExistsException(ConflictException exception) {
        return this.getErrorResponse(exception.getMessage(), exception.getReasonPhrase(), exception.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> generalError(RuntimeException exception) {
        log.error("An unexpected internal error occurred: {}", exception.getMessage());
        return this.getErrorResponse(MESSAGE_ERROR_INTERNAL, DESCRIPTION_ERROR_INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorResponse(String message, String description, HttpStatus httpStatus) {
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.builder()
                .message(message)
                .description(description)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessageResponse, httpStatus);
    }
}
