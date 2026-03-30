package com.odiaz.security.exception;

public class ProductNoFoundException extends  RuntimeException{


    public ProductNoFoundException(String message) {
        super(message);
    }

    public ProductNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }



}
