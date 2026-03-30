package com.odiaz.security.exception;

public class UserNullException extends RuntimeException {

    public UserNullException(String message) {
        super(message);
    }

    public UserNullException(String message, Throwable cause) {
        super(message, cause);
    }

    }
