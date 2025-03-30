package com.master.exception;

public class MastersValidationException extends RuntimeException {
    public MastersValidationException(String message) {
        super(message);
    }

    public MastersValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}