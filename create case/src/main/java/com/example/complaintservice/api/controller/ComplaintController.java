package com.example.complaintservice.api.controller;

import com.example.complaintservice.api.dto.ComplaintDTO;
import com.example.complaintservice.domain.model.Complaint;
import com.example.complaintservice.application.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {
    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    public ResponseEntity<String> createComplaint(@RequestBody ComplaintDTO complaintDTO) {
        Complaint complaint = convertToEntity(complaintDTO);
        String complaintId = complaintService.createComplaint(complaint);
        return ResponseEntity.ok(complaintId);
    }

    private Complaint convertToEntity(ComplaintDTO dto) {
        Complaint complaint = new Complaint();
        complaint.setFirstName(dto.getFirstName());
        // ... set other fields ...
        return complaint;
    }
} 