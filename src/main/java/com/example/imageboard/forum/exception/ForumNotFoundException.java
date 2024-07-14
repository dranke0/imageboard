package com.example.imageboard.forum.exception;

public class ForumNotFoundException extends RuntimeException {

    public ForumNotFoundException(Long id) {
        super("Forum not found with id: " + id);
    }
}

