package com.example.imageboard.entity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class InvalidEntityException extends RuntimeException {

    private final List<String> validationErrors;

    public InvalidEntityException(List<String> validationErrors) {
        super("Entity validation failed: " + formatErrors(validationErrors));
        this.validationErrors = validationErrors;
    }

    public InvalidEntityException(String validationError) {
        this(List.of(validationError));
    }

    private static String formatErrors(List<String> errors) {
        return errors.stream()
                .collect(Collectors.joining(", "));
    }
}
