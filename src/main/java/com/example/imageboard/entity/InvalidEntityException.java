package com.example.imageboard.entity;

import java.util.List;

import java.util.List;

public class InvalidEntityException extends RuntimeException {
    private final List<String> validationErrors;

    public InvalidEntityException(List<String> validationErrors) {
        super("Entity validation failed");
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}

