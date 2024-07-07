package com.example.imageboard.forum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {

    @Query("SELECT b FROM Forum b LEFT JOIN FETCH b.ForumThreads t GROUP BY b ORDER BY COUNT(t) DESC")
    Page<Forum> findMostActiveBoards(Pageable pageable);

    Forum findByName(String name);

    @Query("SELECT b FROM Forum b LEFT JOIN FETCH b.threads t WHERE LOWER(b.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Forum> findByDescriptionContaining(String keyword, Pageable pageable); // Return a Page

    @Query("SELECT t FROM ForumThread t WHERE t.board = :board ORDER BY t.createdAt DESC")
    Page<Thread> findRecentThreadsByBoard(Forum forum, Pageable pageable);

    @Query("SELECT COUNT(t) FROM ForumThread t WHERE t.board = :board")
    Long countThreadsByBoard(Forum forum);

    @Query("SELECT COUNT(p) FROM Comment p WHERE p.Forumthread.board = :board")
    Long countPostsByBoard(Forum forum);

    @Query("SELECT new com.example.imageboard.forum.ForumDto(b.id, b.name, b.description, COUNT(t)) " +
            "FROM Forum b LEFT JOIN b.threads t GROUP BY b")
    Page<ForumDto> findAllBoardSummaries(Pageable pageable);
}

