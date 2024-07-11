package com.example.imageboard.forum.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvalidForumException extends RuntimeException {

    private final Map<String, String> errors;

    public InvalidForumException(BindingResult bindingResult) {
        super("Invalid forum data");
        this.errors = new HashMap<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}

