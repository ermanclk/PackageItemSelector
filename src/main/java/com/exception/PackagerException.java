package com.exception;

public class PackagerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PackagerException(String message) {
        super(message);
    }

    public PackagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
