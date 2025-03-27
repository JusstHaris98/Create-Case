package com.example.complaintservice.integration;

import com.example.complaintservice.config.TestFirestoreConfig;
import com.example.complaintservice.model.Complaint;
import com.example.complaintservice.service.ComplaintService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(TestFirestoreConfig.class)
class ComplaintServiceIntegrationTest {

    @Autowired
    private ComplaintService complaintService;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.cloud.gcp.firestore.emulator.enabled", () -> "true");
        registry.add("spring.cloud.gcp.firestore.host-port", () -> "localhost:9080");
        registry.add("spring.cloud.gcp.firestore.project-id", () -> "test-project");
    }

    @Test
    void shouldCreateComplaint() {
        // Given
        Complaint complaint = new Complaint();
        complaint.setFirstName("John");
        complaint.setLastName("Doe");
        complaint.setAccountNumber("12345678");
        complaint.setSortCode("12-34-56");
        complaint.setComplaintInformation("Test complaint");
        complaint.setDateOfComplaint(LocalDate.now());

        // When
        String complaintId = complaintService.createComplaint(complaint);

        // Then
        assertNotNull(complaintId);
    }
} 