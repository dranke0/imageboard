package com.example.imageboard.thread.exception;

import jakarta.persistence.EntityNotFoundException;

public class ThreadNotFoundException extends EntityNotFoundException {

    public ThreadNotFoundException(Long id) {
        super("Forum thread not found with id: " + id);
    }
}
