package io.github.dr3amer1.backend.presentation.exception;

import io.github.dr3amer1.backend.application.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(
            EntityNotFoundException ex) {

        return Map.of(
                "message",
                ex.getMessage()
        );
    }
}