package com.example.complaintservice.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;

@Data
@Schema(description = "Complaint Request")
public class ComplaintDTO {
    @Schema(description = "Customer's first name", example = "John")
    private String firstName;
    
    // ... rest of the fields ...
} 