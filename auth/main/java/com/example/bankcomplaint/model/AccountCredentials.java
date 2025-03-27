package com.example.bankcomplaint.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Account credentials for authentication")
public class AccountCredentials {
    
    @Schema(description = "Bank sort code", example = "123456")
    private String sortCode;
    
    @Schema(description = "Bank account number", example = "98765432")
    private String accountNumber;

    public AccountCredentials() {
    }

    public AccountCredentials(String sortCode, String accountNumber) {
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
} 