package com.edsoft.okey_app.exception;

public class GameStartException extends RuntimeException {
    public GameStartException(String message) {
        super(message);
    }

    public GameStartException(String message, Throwable cause) {
        super(message, cause);
    }
}