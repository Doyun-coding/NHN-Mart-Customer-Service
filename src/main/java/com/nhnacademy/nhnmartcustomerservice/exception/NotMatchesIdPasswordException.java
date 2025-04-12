package com.nhnacademy.nhnmartcustomerservice.exception;

public class NotMatchesIdPasswordException extends RuntimeException {
    public NotMatchesIdPasswordException(String message) {
        super(message);
    }
}
