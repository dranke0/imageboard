package com.example.imageboard.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponseDto {
    private int status;
    private String error;  // High-level error category (e.g., "Not Found", "Bad Request")
    private String message; // Detailed error message
    private List<String> details; // Optional list of validation errors
    private LocalDateTime timestamp;
    private String path;
}


