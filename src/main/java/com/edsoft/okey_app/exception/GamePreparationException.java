package com.edsoft.okey_app.exception;

public class GamePreparationException extends RuntimeException {
    public GamePreparationException(String message) {
        super(message);
    }

    public GamePreparationException(String message, Throwable cause) {
        super(message, cause);
    }
}