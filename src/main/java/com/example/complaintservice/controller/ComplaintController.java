package com.example.complaintservice.controller;

import com.example.complaintservice.model.Complaint;
import com.example.complaintservice.model.ErrorResponse;
import com.example.complaintservice.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complaints")
@Tag(name = "Complaints", description = "Complaints management APIs")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Operation(summary = "Create a new complaint")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Complaint created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<String> createComplaint(@RequestBody Complaint complaint) {
        String complaintId = complaintService.createComplaint(complaint);
        return ResponseEntity.ok(complaintId);
    }
} 