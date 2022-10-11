package br.com.impacta.moedinhas.api.handler;

import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import br.com.impacta.moedinhas.application.exceptions.ApplicationAuthenticationException;
import br.com.impacta.moedinhas.domain.exception.BadRequestException;
import br.com.impacta.moedinhas.domain.exception.ConflictException;
import br.com.impacta.moedinhas.domain.exception.InternalErrorException;
import br.com.impacta.moedinhas.domain.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String MESSAGE_ERROR_INTERNAL = "Erro interno!";
    private static final String DESCRIPTION_ERROR_INTERNAL = "Ocorreu um erro inesperado entre em contato como Administrador do sistema";

    @ExceptionHandler(ApplicationAuthenticationException.class)
    public ResponseEntity<ErrorMessageResponse> categoryNotFoundException(ApplicationAuthenticationException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ErrorMessageResponse.build(exception.getMessage(), exception.getReasonPhrase()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> categoryNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ErrorMessageResponse.build(exception.getMessage(), exception.getReasonPhrase()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessageResponse> categoryBadRequestException(BadRequestException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ErrorMessageResponse.build(exception.getMessage(), exception.getReasonPhrase()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessageResponse> categoryAlreadyExistsException(ConflictException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ErrorMessageResponse.build(exception.getMessage(), exception.getReasonPhrase()));
    }

    @ExceptionHandler({RuntimeException.class, InternalErrorException.class})
    public ResponseEntity<ErrorMessageResponse> generalError(RuntimeException exception) {
        log.error("An unexpected internal error occurred: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorMessageResponse.build(MESSAGE_ERROR_INTERNAL, DESCRIPTION_ERROR_INTERNAL));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessageResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.builder().message("Bad Request").build();

        ex.getConstraintViolations().forEach(
                violation -> errorMessageResponse.addViolation(violation.getPropertyPath().toString(), violation.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessageResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                      HttpServletRequest request) {
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.builder().message("Bad Request").build();

        BindingResult result = ex.getBindingResult();

        List<ObjectError> globalErrors = result.getGlobalErrors();
        globalErrors.forEach(
                objectError -> errorMessageResponse.addViolation(objectError.getObjectName(), objectError.getDefaultMessage()));

        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.forEach(fieldError -> errorMessageResponse.addViolation(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessageResponse);
    }
}
