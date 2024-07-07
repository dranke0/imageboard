package com.example.imageboard.comment;

// import org.example.imageboard.dtos.projections.PostSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Find all comments within a specific forumThread
    Page<Comment> findByForumThreadId(Long threadId, Pageable pageable); // Pageable for sorting/pagination

    // Find all comments by a specific userEntity
    Page<Comment> findByUserId(Long userId, Pageable pageable);

    // Find all comments containing a keyword in the content
    Page<Comment> findByContentContainingIgnoreCase(String keyword, Pageable pageable);

    // Custom query to find recent comments (you can adjust the date range as needed)
    @Query("SELECT p FROM Comment p WHERE p.createdAt >= CURRENT_DATE - 7 ORDER BY p.createdAt DESC")
    Page<Comment> findRecentComments(Pageable pageable);
    @Query("SELECT p.id as id, SUBSTRING(p.content, 1, 200) as content, p.imageUrl as imageUrl, p.user.username as authorUsername, p.createdAt as createdAt FROM Comment p")
    Page<CommentDto> findComments(Pageable pageable); // Pageable for pagination
}
