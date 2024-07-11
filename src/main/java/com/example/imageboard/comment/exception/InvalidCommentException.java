package com.example.imageboard.comment.exception; // Make sure this matches your package structure

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvalidCommentException extends RuntimeException {

    private final Map<String, String> errors;

    public InvalidCommentException(BindingResult bindingResult) {
        super("Invalid comment data");
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
