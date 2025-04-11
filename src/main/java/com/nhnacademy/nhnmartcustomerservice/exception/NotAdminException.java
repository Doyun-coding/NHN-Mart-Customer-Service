package com.nhnacademy.nhnmartcustomerservice.exception;

public class NotAdminException extends RuntimeException {
    public NotAdminException(String message) {
        super(message);
    }
}
