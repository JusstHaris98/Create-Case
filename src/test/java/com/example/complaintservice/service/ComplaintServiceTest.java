package com.example.complaintservice.service;

import com.example.complaintservice.exception.ComplaintValidationException;
import com.example.complaintservice.model.Complaint;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComplaintServiceTest {

    @Mock
    private Firestore firestore;
    
    @Mock
    private CollectionReference collectionReference;
    
    @Mock
    private ApiFuture<DocumentReference> futureDoc;
    
    @Mock
    private DocumentReference documentReference;

    @InjectMocks
    private ComplaintService complaintService;

    @BeforeEach
    void setUp() {
        complaintService = new ComplaintService(firestore);
        when(firestore.collection("complaints")).thenReturn(collectionReference);
        when(collectionReference.add(any(Complaint.class))).thenReturn(futureDoc);
        when(documentReference.getId()).thenReturn("test-id");
    }

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