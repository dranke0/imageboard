package com.example.imageboard.thread;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThreadRepository extends JpaRepository<ForumThread, Long> {
    Optional<ForumThread> findById(Long id);
}
