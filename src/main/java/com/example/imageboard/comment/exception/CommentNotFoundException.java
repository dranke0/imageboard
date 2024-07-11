package com.example.imageboard.comment.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Long id) {
        super("Comment not found with ID: " + id);
    }
}

