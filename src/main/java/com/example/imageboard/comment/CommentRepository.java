package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Find comments by thread ID
    Page<Comment> findByForumThreadId(Long threadId, Pageable pageable);

    // Find comments by user ID
    Page<Comment> findByUserId(Long userId, Pageable pageable);

    // Find comments containing a keyword (case-insensitive)
    Page<Comment> findByContentContainingIgnoreCase(String keyword, Pageable pageable);

    // Find comments by forum thread ID
    @Query("SELECT c FROM Comment c WHERE c.forumThread.id = :threadId")
    Page<Comment> findCommentsByThreadId(Long threadId, Pageable pageable);

    // Find comments containing a keyword for a specific forum thread (case-insensitive)
    @Query("SELECT c FROM Comment c WHERE c.forumThread.id = :threadId AND LOWER(c.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Comment> searchCommentsByKeywordAndThreadId(Long threadId, String keyword, Pageable pageable);

    // Find all comments and return CommentDtos directly
    @Query("SELECT new com.example.imageboard.comment.dto.CommentDto(c.id, c.content, c.imageUrl, c.user.username, c.createdAt) FROM Comment c")
    Page<CommentDto> findAllCommentDtos(Pageable pageable);
}


