package com.example.imageboard.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor; // Added Lombok annotation
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@RequiredArgsConstructor
public class ResourceNotFoundException extends EntityNotFoundException {

    private final String resourceType;
    private final String identifier;

    private static String getIdentifierName(String resourceType) {
        return (resourceType.endsWith("s") || resourceType.endsWith("S")) ? "ID" : "id"; // Handle case-insensitive plural
    }
}


