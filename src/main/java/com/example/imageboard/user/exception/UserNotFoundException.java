package com.example.imageboard.user.exception; // Make sure this matches your package structure

public class UserNotFoundException extends RuntimeException {

    private final Long userId;

    public UserNotFoundException(Long userId) {
        super("User not found with ID: " + userId + " (Error code: UNF-" + userId + ")");
        this.userId = userId;
    }

    public UserNotFoundException(Long userId, String message) {
        super(message + " (User ID: " + userId + ")");
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
