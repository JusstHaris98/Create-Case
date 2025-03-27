package com.example.complaintservice.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@Configuration
public class FirestoreConfig {
    
    private final ResourceLoader resourceLoader;
    
    @Value("${spring.cloud.gcp.credentials.location:classpath:serviceAccount.json}")
    private String credentialsLocation;
    
    public FirestoreConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public Firestore firestore() throws IOException {
        Resource credentialsResource = resourceLoader.getResource(credentialsLocation);
        
        if (!credentialsResource.exists()) {
            throw new IllegalStateException(
                "Firestore credentials file not found at: " + credentialsLocation);
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(
            credentialsResource.getInputStream());

        FirestoreOptions options = FirestoreOptions.newBuilder()
                .setCredentials(credentials)
                .build();
                
        return options.getService();
    }
}
