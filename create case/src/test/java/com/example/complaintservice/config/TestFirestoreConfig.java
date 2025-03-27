package com.example.complaintservice.config;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestFirestoreConfig {

    @Bean
    @Primary
    public Firestore firestoreEmulator() {
        FirestoreOptions options = FirestoreOptions.getDefaultInstance().toBuilder()
            .setProjectId("test-project")
            .setHost("localhost:9080")  // Firestore emulator default port
            .setCredentials(null)  // No credentials needed for emulator
            .build();
        return options.getService();
    }
} 