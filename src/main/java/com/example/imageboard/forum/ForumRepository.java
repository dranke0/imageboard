package com.example.imageboard.forum;

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forumThread.ForumThread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {

    @Query("SELECT f FROM Forum f LEFT JOIN FETCH f.forumThreads")
    Page<Forum> findAllWithThreads(Pageable pageable);

    @Query("SELECT f FROM Forum f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Forum> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT f FROM Forum f WHERE LOWER(f.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Forum> findByDescriptionContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("SELECT ft FROM ForumThread ft WHERE ft.forum = :forum ORDER BY ft.createdAt DESC")
    Page<ForumThread> findRecentThreadsByForum(Forum forum, Pageable pageable);

    @Query("SELECT COUNT(ft) FROM ForumThread ft WHERE ft.forum = :forum")
    Long countThreadsByForum(Forum forum);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.forumThread.forum = :forum")
    Long countPostsByForum(Forum forum);

    @Query("SELECT new com.example.imageboard.forum.dto.ForumDto(f.id, f.name, f.description, COUNT(ft)) " +
            "FROM Forum f LEFT JOIN f.forumThreads ft GROUP BY f.id, f.name, f.description")
    Page<ForumDto> findAllForumDtos(Pageable pageable);

}


