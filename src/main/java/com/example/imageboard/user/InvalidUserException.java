package com.example.imageboard.user;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class InvalidUserException extends RuntimeException {

    private final BindingResult bindingResult;

    public InvalidUserException(BindingResult bindingResult) {
        super("Invalid user input");
        this.bindingResult = bindingResult;
    }

    public List<FieldError> getFieldErrors() {
        return bindingResult.getFieldErrors();
    }

    @Override
    public String getMessage() {
        return getFieldErrors().stream()
                .map(err -> String.format("%s: %s", err.getField(), err.getDefaultMessage()))
                .reduce((s1, s2) -> s1 + ", " + s2)
                .orElse("Validation failed");
    }
}
