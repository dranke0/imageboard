package com.example.imageboard.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {

    @Query(value = "SELECT * FROM Forum WHERE id = :forumId", nativeQuery = true)
    Optional<Forum> findForumById(@Param("forumId") Long forumId);
}



