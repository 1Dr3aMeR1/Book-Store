package io.github.dr3amer1.backend.presentation.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            EntityNotFoundException.class
    )
    public ResponseEntity<ApiErrorResponse>
    handleEntityNotFound(
            EntityNotFoundException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ApiErrorResponse(
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(
            IllegalStateException.class
    )
    public ResponseEntity<ApiErrorResponse>
    handleIllegalState(
            IllegalStateException exception
    ) {

        return ResponseEntity
                .badRequest()
                .body(
                        new ApiErrorResponse(
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<ApiErrorResponse>
    handleValidation(
            MethodArgumentNotValidException exception
    ) {

        String message =
                exception
                        .getBindingResult()
                        .getFieldErrors()
                        .get(0)
                        .getDefaultMessage();

        return ResponseEntity
                .badRequest()
                .body(
                        new ApiErrorResponse(
                                message
                        )
                );
    }

    @ExceptionHandler(
            AccessDeniedException.class
    )
    public ResponseEntity<ApiErrorResponse>
    handleAccessDenied(
            AccessDeniedException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(
                        new ApiErrorResponse(
                                "Access denied"
                        )
                );
    }

    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<ApiErrorResponse>
    handleException(
            Exception exception
    ) {

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(
                        new ApiErrorResponse(
                                "Internal server error"
                        )
                );
    }
}