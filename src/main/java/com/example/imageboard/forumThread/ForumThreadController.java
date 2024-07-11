package com.example.imageboard.forumThread;

import com.example.imageboard.forumThread.dto.ForumThreadDto;
import com.example.imageboard.forumThread.exception.ForumThreadNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // Add for authorization
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/threads")
@RequiredArgsConstructor
public class ForumThreadController {

    private final ForumThreadService forumThreadService;

    @GetMapping
    public ResponseEntity<Page<ForumThreadDto>> getAllForumThreads(Pageable pageable) {
        return ResponseEntity.ok(forumThreadService.getAllForumThreads(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumThreadDto> getForumThreadById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(forumThreadService.getForumThreadById(id));
        } catch (ForumThreadNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/forum/{forumId}")
    public ResponseEntity<Page<ForumThreadDto>> getForumThreadsByForumId(@PathVariable Long forumId, Pageable pageable) {
        return ResponseEntity.ok(forumThreadService.getForumThreadsByForumId(forumId, pageable));
    }

    @GetMapping("/most-active")
    public ResponseEntity<Page<ForumThreadDto>> getMostActiveForumThreads(Pageable pageable) {
        LocalDateTime startDate = LocalDateTime.now().minusDays(1); // Example: most active in the last 24 hours
        return ResponseEntity.ok(forumThreadService.getMostActiveForumThreads(startDate, pageable));
    }

    @GetMapping("/trending")
    public ResponseEntity<Page<ForumThreadDto>> getTrendingForumThreads(Pageable pageable) {
        LocalDateTime startDate = LocalDateTime.now().minusWeeks(1); // Example: trending in the last week
        return ResponseEntity.ok(forumThreadService.getTrendingForumThreads(startDate, pageable));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()") // Ensure user is authenticated to create threads
    public ResponseEntity<ForumThreadDto> createForumThread(@Valid @RequestBody ForumThreadDto forumThreadDto) {
        ForumThreadDto createdThread = forumThreadService.createForumThread(forumThreadDto);
        return ResponseEntity.created(URI.create("/api/threads/" + createdThread.getId())).body(createdThread);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and (hasRole('ADMIN') or #threadDto.author.id == authentication.principal.id)")
    public ResponseEntity<ForumThreadDto> updateForumThread(@PathVariable Long id, @Valid @RequestBody ForumThreadDto threadDto) {
        try {
            return ResponseEntity.ok(forumThreadService.updateForumThread(id, threadDto));
        } catch (ForumThreadNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and (hasRole('ADMIN') or #threadDto.author.id == authentication.principal.id)")
    public ResponseEntity<Void> deleteForumThread(@PathVariable Long id) {
        try {
            forumThreadService.deleteForumThread(id);
            return ResponseEntity.noContent().build();
        } catch (ForumThreadNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
