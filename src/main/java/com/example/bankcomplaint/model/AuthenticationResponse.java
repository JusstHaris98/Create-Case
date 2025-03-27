package com.example.bankcomplaint.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response")
public class AuthenticationResponse {
    
    @Schema(description = "Authentication status", example = "success")
    private String status;
    
    @Schema(description = "Response message", example = "Authentication successful")
    private String message;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
} 