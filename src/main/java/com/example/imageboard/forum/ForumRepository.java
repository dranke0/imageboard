package com.example.imageboard.forum;

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.thread.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {

    // Find all forums with their threads (eagerly fetching threads)
    @Query("SELECT f FROM Forum f LEFT JOIN FETCH f.forumThreads")
    List<Forum> findAllForumsWithThreads();

    // Find all forums, but without fetching threads (useful for listing forums)
    List<Forum> findAll(); // Using Spring Data's built-in findAll()

    // Find a forum by its ID (returns Optional)
    Optional<Forum> findById(Long id);

    // Find a forum by its name (returns Optional)
    Optional<Forum> findByName(String name);

    // Find forums by name (case-insensitive)
    List<Forum> findByNameContainingIgnoreCase(String name);

    // Find forums by description (case-insensitive)
    List<Forum> findByDescriptionContainingIgnoreCase(String keyword);

    // Find recent threads for a specific forum
    @Query("SELECT ft FROM ForumThread ft WHERE ft.forum = :forum ORDER BY ft.createdAt DESC")
    List<ForumThread> findRecentThreadsByForum(Forum forum);

    // Count threads in a specific forum
    @Query("SELECT COUNT(ft) FROM ForumThread ft WHERE ft.forum = :forum")
    Long countThreadsByForum(Forum forum);

    // Count posts (comments) in a specific forum
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.forumThread.forum = :forum")
    Long countPostsByForum(Forum forum);

    // Find all ForumDtos, calculate thread count on the fly

    @Query("SELECT new com.example.imageboard.forum.dto.ForumDto(f.id, f.name, f.description, CAST(COUNT(ft) AS long)) " +
            "FROM Forum f LEFT JOIN f.forumThreads ft GROUP BY f.id, f.name, f.description")
    List<ForumDto> findAllForumDtos();

    @Query("SELECT f FROM Forum f")
    List<Forum> findAllForums(); // Correct method name


}



