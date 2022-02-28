package com.example.unitech.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String msg) {
        super(msg);
    }

    public AccountNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
