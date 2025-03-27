package com.example.complaintservice.service;

import com.example.complaintservice.exception.ComplaintValidationException;
import com.example.complaintservice.model.Complaint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.google.cloud.firestore.Firestore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ComplaintServiceTest {

    @Mock
    private Firestore firestore;

    @InjectMocks
    private ComplaintService complaintService;

    @Test
    void whenComplaintIsValid_thenNoExceptionIsThrown() {
        Complaint complaint = new Complaint();
        complaint.setFirstName("John");
        complaint.setLastName("Doe");
        complaint.setAccountNumber("12345678");
        complaint.setSortCode("12-34-56");
        complaint.setComplaintInformation("Test complaint");
        complaint.setDateOfComplaint(LocalDate.now());

        assertDoesNotThrow(() -> complaintService.createComplaint(complaint));
    }

    @Test
    void whenComplaintHasInvalidSortCode_thenThrowsException() {
        Complaint complaint = new Complaint();
        complaint.setSortCode("123456"); // Invalid format

        assertThrows(ComplaintValidationException.class, 
            () -> complaintService.createComplaint(complaint));
    }
} 