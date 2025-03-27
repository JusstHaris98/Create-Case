package com.example.complaintservice.application.service;

import com.example.complaintservice.domain.model.Complaint;
import com.example.complaintservice.infrastructure.persistence.FirestoreRepository;
import com.example.complaintservice.common.exception.ComplaintValidationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ComplaintService {
    private final FirestoreRepository repository;

    public ComplaintService(FirestoreRepository repository) {
        this.repository = repository;
    }

    public String createComplaint(Complaint complaint) {
        validateComplaint(complaint);
        return repository.saveComplaint(complaint);
    }

    // ... rest of the validation methods ...
} 