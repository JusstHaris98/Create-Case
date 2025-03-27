package com.example.complaintservice.exception;

public class FirestoreOperationException extends ComplaintException {
    public FirestoreOperationException(String message, Throwable cause) {
        super(message, cause);
    }
} 