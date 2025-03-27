package com.example.bankcomplaint.service;

import com.example.bankcomplaint.model.AccountCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationService {
    private final ObjectMapper objectMapper;
    private List<Map<String, String>> validAccounts;

    public AuthenticationService() {
        this.objectMapper = new ObjectMapper();
        loadValidAccounts();
    }

    private void loadValidAccounts() {
        try {
            ClassPathResource resource = new ClassPathResource("valid_accounts.json");
            Map<String, List<Map<String, String>>> accountsData = objectMapper.readValue(resource.getInputStream(), Map.class);
            this.validAccounts = accountsData.get("accounts");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load valid accounts", e);
        }
    }

    public boolean authenticate(AccountCredentials credentials) {
        return validAccounts.stream()
                .anyMatch(account -> 
                    account.get("sortCode").equals(credentials.getSortCode()) &&
                    account.get("accountNumber").equals(credentials.getAccountNumber())
                );
    }
} 