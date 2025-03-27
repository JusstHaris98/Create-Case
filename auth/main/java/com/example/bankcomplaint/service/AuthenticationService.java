package com.example.bankcomplaint.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.bankcomplaint.model.AccountCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final ObjectMapper objectMapper;
    private List<Map<String, String>> validAccounts;

    public AuthenticationService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        loadValidAccounts();
    }

    private void loadValidAccounts() {
        try (InputStream is = getClass().getResourceAsStream("/valid_accounts.json")) {
            if (is == null) {
                throw new RuntimeException("valid_accounts.json not found in classpath");
            }
            validAccounts = objectMapper.readValue(is, List.class);
            logger.info("Loaded {} valid accounts", validAccounts.size());
        } catch (IOException e) {
            logger.error("Error loading valid_accounts.json", e);
            throw new RuntimeException("Failed to load valid accounts", e);
        }
    }

    public boolean authenticate(AccountCredentials credentials) {
        if (credentials == null || credentials.getSortCode() == null || credentials.getAccountNumber() == null) {
            logger.warn("Invalid credentials: null values");
            return false;
        }

        return validAccounts.stream()
                .anyMatch(account -> 
                    account.get("sortCode").equals(credentials.getSortCode()) &&
                    account.get("accountNumber").equals(credentials.getAccountNumber())
                );
    }
} 