package com.example.complaintservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;

@Data
@Schema(description = "Complaint Details")
public class Complaint {
    @Schema(description = "Customer's first name", example = "John")
    private String firstName;

    @Schema(description = "Customer's last name", example = "Doe")
    private String lastName;

    @Schema(description = "Customer's account number", example = "12345678")
    private String accountNumber;

    @Schema(description = "Bank sort code", example = "12-34-56")
    private String sortCode;

    @Schema(description = "Detailed complaint information", 
            example = "Issue with recent transaction")
    private String complaintInformation;

    @Schema(description = "Date when complaint was filed", 
            example = "2024-03-20")
    private LocalDate dateOfComplaint;
} 