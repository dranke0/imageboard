package com.example.imageboard.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public class InvalidUserException extends MethodArgumentNotValidException { // Extend a more specific exception

    public InvalidUserException(BindingResult bindingResult) {
        super(null, bindingResult);  // Pass BindingResult to the super constructor
    }

    public List<FieldError> getFieldErrors() {
        return this.getBindingResult().getFieldErrors();
    }

    // Optional: Structured Error Response
    public List<CustomFieldError> getCustomFieldErrors() {
        return getFieldErrors().stream()
                .map(err -> new CustomFieldError(err.getField(), err.getDefaultMessage()))
                .toList();
    }

    // ... (You can add error codes if needed)

    // Nested class for a custom field error (optional)
    @Getter
    @AllArgsConstructor
    public static class CustomFieldError {
        private String field;
        private String message;
    }
}

