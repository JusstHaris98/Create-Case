package com.example.complaintservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI complaintsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Complaints API")
                        .description("API for managing customer complaints")
                        .version("1.0"));
    }
} 