package io.github.dr3amer1.backend.presentation.exception;

public class EntityNotFoundException
        extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}