package com.example.bankcomplaint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI bankComplaintOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bank Complaint API")
                        .description("API for handling bank complaints and authentication")
                        .version("1.0"));
    }
} 