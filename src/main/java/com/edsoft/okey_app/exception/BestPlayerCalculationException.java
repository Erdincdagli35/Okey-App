package com.edsoft.okey_app.exception;

public class BestPlayerCalculationException extends RuntimeException {
    public BestPlayerCalculationException(String message) {
        super(message);
    }

    public BestPlayerCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}