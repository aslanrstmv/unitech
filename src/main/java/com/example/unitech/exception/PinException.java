package com.example.unitech.exception;

public class PinException extends RuntimeException {

    public PinException() {
        super();
    }

    public PinException(String message) {
        super(message);
    }

    protected PinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
