package com.example.imageboard.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Find comments by thread ID (built-in method)
    Page<Comment> findByForumThreadId(Long threadId, Pageable pageable);

    // Find comments by user ID (built-in method)
    Page<Comment> findByUserId(Long userId, Pageable pageable);

    // Find comments containing a keyword (case-insensitive) (built-in method)
    Page<Comment> findByContentContainingIgnoreCase(String keyword, Pageable pageable);

    // Find comments by thread ID, sorted by creation time (descending)
    Page<Comment> findByForumThreadIdOrderByCreatedAtDesc(Long threadId, Pageable pageable);

    // Find comments by thread ID and user ID
    Page<Comment> findByForumThreadIdAndUserId(Long threadId, Long userId, Pageable pageable);

    // Find comments containing a keyword for a specific forum thread (case-insensitive)
    Page<Comment> findByForumThreadIdAndContentContainingIgnoreCase(Long threadId, String keyword, Pageable pageable);

    // Find comments by thread ID, eagerly fetching thread and user (to avoid N+1 query problem)
    @Query("SELECT c FROM Comment c JOIN FETCH c.forumThread ft JOIN FETCH c.user u WHERE ft.id = :threadId")
    Page<Comment> findCommentsByThreadIdWithUserAndThread(Long threadId, Pageable pageable);
}




