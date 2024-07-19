package com.example.imageboard.user.exception; // Make sure this matches your package structure

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserNotFoundException extends RuntimeException {

    private final Long userId;

    public UserNotFoundException(Long userId, String message) {
        super(message + " (User ID: " + userId + ")");
        this.userId = userId;
    }
}
