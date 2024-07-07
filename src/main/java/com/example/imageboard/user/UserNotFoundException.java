package com.example.imageboard.user;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String s) {
        super(s);
    }
}
