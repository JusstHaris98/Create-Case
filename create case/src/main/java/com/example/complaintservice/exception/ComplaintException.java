package com.example.complaintservice.exception;

public class ComplaintException extends RuntimeException {
    public ComplaintException(String message) {
        super(message);
    }

    public ComplaintException(String message, Throwable cause) {
        super(message, cause);
    }
} 