package com.example.imageboard.user.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Include only non-null fields
public class ErrorResponse {
    private final HttpStatus status;
    private final String message;
    private final List<InvalidUserException.CustomFieldError> errors;
    private final LocalDateTime timestamp;

    public ErrorResponse(HttpStatus status, String message, List<InvalidUserException.CustomFieldError> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }
}

