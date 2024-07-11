package com.example.imageboard.forumThread.exception;

public class ForumThreadNotFoundException extends RuntimeException {

    public ForumThreadNotFoundException(Long id) {
        super("Forum thread not found with id: " + id);
    }
}
