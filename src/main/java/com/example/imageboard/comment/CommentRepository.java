package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByForumThreadId(Long threadId, Pageable pageable);
    Page<Comment> findByUserId(Long userId, Pageable pageable);

    // Use a more explicit method name
    Page<Comment> findByContentContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("SELECT new com.example.imageboard.comment.dto.CommentDto(c.id, c.content, c.imageUrl, c.user.username, c.createdAt) " +
            "FROM Comment c")
    Page<CommentDto> findAllCommentDtos(Pageable pageable); // Changed to return CommentDto directly
}

