package com.example.imageboard.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Find all comments for a specific thread ID
    List<Comment> findByForumThreadId(Long threadId);

    // Find a comment by its ID (returns Optional)
    Optional<Comment> findById(Long id);

    // Find comments by user ID
    List<Comment> findByUserId(Long userId);

    // Find comments containing a keyword (case-insensitive)
    List<Comment> findByContentContainingIgnoreCase(String keyword);

    // Find comments for a specific thread ID, sorted by creation time (descending)
    List<Comment> findByForumThreadIdOrderByCreatedAtDesc(Long threadId);

    // Find comments by thread ID and user ID
    Optional<Comment> findByForumThreadIdAndUserId(Long threadId, Long userId);

    // Find comments containing a keyword for a specific forum thread (case-insensitive)
    List<Comment> findByForumThreadIdAndContentContainingIgnoreCase(Long threadId, String keyword);

    // Find comments by thread ID, eagerly fetching thread and user (to avoid N+1 query problem)
    @Query("SELECT c FROM Comment c JOIN FETCH c.forumThread ft JOIN FETCH c.user u WHERE ft.id = :threadId")
    List<Comment> findCommentsByThreadIdWithUserAndThread(Long threadId);
}
