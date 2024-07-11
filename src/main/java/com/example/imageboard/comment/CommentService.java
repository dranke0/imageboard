package com.example.imageboard.comment;

import com.example.imageboard.comment.exception.CommentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Get all comments (potentially with pagination or sorting later)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Get comments by user ID (assuming Comment has a user field)
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    // Get comments for a specific post (assuming Comment has a post field)
    public List<Comment> getCommentsByPostId(Long postId) {
        // Example using a custom query if not directly supported
        return commentRepository.findCommentsByPostId(postId);
    }

    // Create a new comment
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Update an existing comment (assuming you find it first)
    public Comment updateComment(Comment updatedComment) {
        if (commentRepository.existsById(updatedComment.getId())) {
            return commentRepository.save(updatedComment);
        } else {
            throw new CommentNotFoundException("Comment not found with ID: " + updatedComment.getId());
        }
    }

    // Delete a comment by ID
    public void deleteComment(Long commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
        } else {
            throw new CommentNotFoundException("Comment not found with ID: " + commentId);
        }
    }

    // ... other comment-related operations ...
}


