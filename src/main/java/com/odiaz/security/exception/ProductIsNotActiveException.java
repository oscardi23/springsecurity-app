package com.odiaz.security.exception;

public class ProductIsNotActiveException extends RuntimeException {

    public ProductIsNotActiveException(String message) {
        super(message);
    }

    public ProductIsNotActiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
