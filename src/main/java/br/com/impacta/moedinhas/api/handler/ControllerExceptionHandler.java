package br.com.impacta.moedinhas.api.handler;

import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import br.com.impacta.moedinhas.domain.exception.CategoryConflictException;
import br.com.impacta.moedinhas.domain.exception.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final String MESSAGE_ERROR_INTERNAL = "Erro interno!";
    private final String DESCRIPTION_ERROR_INTERNAL = "Ocorreu um erro inesperado entre em contato como Administrador do sistema";

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> categoryNotFoundException(CategoryNotFoundException exception) {
        return this.getErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryConflictException.class)
    public ResponseEntity<ErrorMessageResponse> categoryAlreadyExistsException(CategoryConflictException exception) {
        return this.getErrorResponse(exception.getMessage(), HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> generalError(RuntimeException exception) {
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
