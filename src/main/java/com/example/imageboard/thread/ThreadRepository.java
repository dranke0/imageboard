package com.example.imageboard.thread;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<ForumThread, Long> {
    @Query("SELECT t FROM ForumThread t WHERE t.forum.id = :forumId")
    List<ForumThread> findByForumId(@Param("forumId") Long forumId);
}
