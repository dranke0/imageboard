package com.example.imageboard.comment.exception;

public class CommentNotFoundException extends RuntimeException {

    private final Long commentId;

    public CommentNotFoundException(Long commentId) {
        super("Comment not found with ID: " + commentId + " (Error code: CNF-" + commentId + ")");
        this.commentId = commentId;
    }

    public CommentNotFoundException(Long commentId, String message) {
        super(message + " (Comment ID: " + commentId + ")");
        this.commentId = commentId;
    }

    public Long getCommentId() {
        return commentId;
    }
}
