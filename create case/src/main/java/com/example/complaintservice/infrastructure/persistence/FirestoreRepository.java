package com.example.complaintservice.infrastructure.persistence;

import com.example.complaintservice.domain.model.Complaint;
import com.example.complaintservice.common.exception.FirestoreOperationException;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class FirestoreRepository {
    private final Firestore firestore;

    public FirestoreRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public String saveComplaint(Complaint complaint) {
        try {
            return firestore.collection("complaints")
                    .add(complaint)
                    .get()
                    .getId();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new FirestoreOperationException("Failed to save complaint", e);
        }
    }
} 