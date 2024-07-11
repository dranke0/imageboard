package com.example.imageboard.forumThread;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ForumThreadRepository extends JpaRepository<ForumThread, Long> {
    @EntityGraph(value = "ForumThread.comments")
    Page<ForumThread> findByUserId(Long userId, Pageable pageable);

    @EntityGraph(value = "ForumThread.comments")
    Page<ForumThread> findByStatus(ForumThreadStatus status, Pageable pageable);

    @EntityGraph(value = "ForumThread.comments")
    Page<ForumThread> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    @EntityGraph(value = "ForumThread.comments")
    Page<ForumThread> findByForumIdAndStatus(Long forumId, ForumThreadStatus status, Pageable pageable);

    // Most Active Threads (Example: by Comment Count in the Last 24 Hours)
    @Query("SELECT ft FROM ForumThread ft " +
            "WHERE ft.createdAt >= :startDate " + // Optional: filter by forumId as well
            "ORDER BY (SELECT COUNT(c) FROM Comment c WHERE c.forumThread = ft AND c.createdAt >= :startDate) DESC")
    @EntityGraph(value = "ForumThread.comments") // Ensure comments are fetched
    Page<ForumThread> findMostActiveForumThreads(LocalDateTime startDate, Pageable pageable);

    // Trending ForumThreads (Example: by Comment Count in the Last Week)
    // Use the same query as findMostActiveForumThreads, but adjust startDate in the service layer
    @EntityGraph(value = "ForumThread.comments") // Ensure comments are fetched
    Page<ForumThread> findTrendingForumThreads(LocalDateTime startDate, Pageable pageable);

    @EntityGraph(value = "ForumThread.comments")
    Page<ForumThread> findByForumId(Long forumId, Pageable pageable); // Added Pageable
}
