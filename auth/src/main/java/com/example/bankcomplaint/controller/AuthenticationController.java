package com.example.bankcomplaint.controller;

import com.example.bankcomplaint.model.AccountCredentials;
import com.example.bankcomplaint.model.AuthenticationResponse;
import com.example.bankcomplaint.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate user credentials", 
              description = "Validates the provided sort code and account number against the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Authentication successful"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials"),
        @ApiResponse(responseCode = "400", description = "Invalid request format")
    })
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Parameter(description = "Account credentials containing sort code and account number", required = true)
            @RequestBody AccountCredentials credentials) {
        
        logger.info("Authentication attempt for sort code: {}", credentials.getSortCode());
        
        boolean isValid = authenticationService.authenticate(credentials);
        
        if (isValid) {
            logger.info("Authentication successful for sort code: {}", credentials.getSortCode());
            return ResponseEntity.ok(new AuthenticationResponse("success", "Authentication successful"));
        }
        
        logger.warn("Authentication failed for sort code: {}", credentials.getSortCode());
        return ResponseEntity.status(401)
                .body(new AuthenticationResponse("error", "Invalid sort code or account number"));
    }
} 