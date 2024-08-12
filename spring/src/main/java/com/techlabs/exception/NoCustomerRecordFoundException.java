package com.techlabs.exception;

public class NoCustomerRecordFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoCustomerRecordFoundException(String message) {
        super(message);
    }
}