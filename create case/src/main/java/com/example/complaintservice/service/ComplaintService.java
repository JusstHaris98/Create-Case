package com.example.complaintservice.service;

import com.example.complaintservice.exception.ComplaintValidationException;
import com.example.complaintservice.exception.FirestoreOperationException;
import com.example.complaintservice.model.Complaint;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.ExecutionException;

@Service
public class ComplaintService {

    @Autowired
    private Firestore firestore;

    public String createComplaint(Complaint complaint) {
        validateComplaint(complaint);
        try {
            return firestore.collection("complaints")
                    .add(complaint)
                    .get()
                    .getId();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new FirestoreOperationException("Failed to create complaint in database", e);
        }
    }

    private void validateComplaint(Complaint complaint) {
        if (complaint == null) {
            throw new ComplaintValidationException("Complaint cannot be null");
        }
        if (!StringUtils.hasText(complaint.getFirstName())) {
            throw new ComplaintValidationException("First name is required");
        }
        if (!StringUtils.hasText(complaint.getLastName())) {
            throw new ComplaintValidationException("Last name is required");
        }
        if (!StringUtils.hasText(complaint.getAccountNumber())) {
            throw new ComplaintValidationException("Account number is required");
        }
        if (!StringUtils.hasText(complaint.getSortCode())) {
            throw new ComplaintValidationException("Sort code is required");
        }
        if (!StringUtils.hasText(complaint.getComplaintInformation())) {
            throw new ComplaintValidationException("Complaint information is required");
        }
        if (complaint.getDateOfComplaint() == null) {
            throw new ComplaintValidationException("Date of complaint is required");
        }
        
        // Add sort code format validation
        if (!complaint.getSortCode().matches("\\d{2}-\\d{2}-\\d{2}")) {
            throw new ComplaintValidationException("Sort code must be in format XX-XX-XX");
        }
        
        // Add account number format validation
        if (!complaint.getAccountNumber().matches("\\d{8}")) {
            throw new ComplaintValidationException("Account number must be 8 digits");
        }
    }
} 