package br.com.barber_shop_api.handlers;

import br.com.barber_shop_api.controllers.response.ProblemResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Log4j2
@ControllerAdvice
public class BarberShopExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(final Exception ex, final WebRequest request) {
        log.error("handleUncaught: ", ex);
        var status = INTERNAL_SERVER_ERROR;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        log.warn("Validation error: {}", ex.getMessage());

        // Concatena os erros em uma única string
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));

        var response = ProblemResponse.builder()
                .status(BAD_REQUEST.value())
                .timestamp(OffsetDateTime.now())
                .message(errorMessage)
                .build();

        return handleExceptionInternal(ex, response, headers, BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        log.error("Data integrity violation: ", ex);

        var response = ProblemResponse.builder()
                .status(CONFLICT.value())
                .timestamp(OffsetDateTime.now())
                .message("Erro de integridade referencial ou tentativa de duplicação de dados")
                .build();

        return handleExceptionInternal(ex, response, new HttpHeaders(), CONFLICT, request);
    }
}

