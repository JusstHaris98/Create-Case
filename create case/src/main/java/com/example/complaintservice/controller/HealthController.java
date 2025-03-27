package com.example.complaintservice.controller;

import com.google.cloud.firestore.Firestore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final Firestore firestore;

    public HealthController(Firestore firestore) {
        this.firestore = firestore;
    }

    @GetMapping("/health/firestore")
    public ResponseEntity<String> checkFirestoreConnection() {
        try {
            // Try to list collections (lightweight operation)
            firestore.listCollections();
            return ResponseEntity.ok("Firestore connection successful");
        } catch (Exception e) {
            return ResponseEntity.status(503)
                .body("Firestore connection failed: " + e.getMessage());
        }
    }
} 