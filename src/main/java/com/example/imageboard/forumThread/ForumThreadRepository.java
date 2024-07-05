package com.example.imageboard.forumThread;

import com.example.imageboard.enums.ForumThreadStatus;
//import org.example.imageboard.dtos.projections.ThreadSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ForumThreadRepository extends JpaRepository<ForumThread, Long> {

    // Find all forumThreads created by a specific user
    Page<ForumThread> findByUserId(Long userId, Pageable pageable);

    // Find all forumThreads with a specific status (e.g., OPEN, LOCKED, ARCHIVED)
    Page<ForumThread> findByStatus(ForumThreadStatus status, Pageable pageable);

    // Find all forumThreads containing a keyword in the title (case-insensitive)
    Page<ForumThread> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    // 1. Combined Filtering (Example: by Forum and Status)
    Page<ForumThread> findByForumIdAndStatus(Long boardId, ForumThreadStatus status, Pageable pageable);

    // 2. Recent Posts Count
    @Query("SELECT t, COUNT(p) as recentPostCount " +
            "FROM ForumThread t LEFT JOIN t.comments p " +
            "WHERE t.board.id = :boardId AND p.createdAt >= :startDate " +
            "GROUP BY t ORDER BY recentPostCount DESC")
    Page<ForumThreadDto> findForumThreadsWithRecentPostCountByBoardId(Long boardId, LocalDateTime startDate, Pageable pageable);

    // 3. Most Active Threads (Example: by Comment Count in the Last 24 Hours)
    @Query("SELECT t FROM ForumThread t " +
            "WHERE t.createdAt >= :startDate " + // Optional: filter by boardId as well
            "ORDER BY (SELECT COUNT(p) FROM Comment p WHERE p.forumThread = t AND p.createdAt >= :startDate) DESC")
    Page<ForumThread> findMostActiveForumThreads(LocalDateTime startDate, Pageable pageable);

    // 4. Trending ForumThreads (Example: by Comment Count in the Last Week)
    @Query("SELECT t FROM ForumThread t " +
            "WHERE t.createdAt >= :startDate " + // Optional: filter by boardId as well
            "ORDER BY (SELECT COUNT(p) FROM Comment p WHERE p.forumThread = t AND p.createdAt >= :startDate) DESC")
    Page<ForumThread> findTrendingForumThreads(LocalDateTime startDate, Pageable pageable);

    @EntityGraph
    List<ForumThread> findByForumId(Long forumId);
}



