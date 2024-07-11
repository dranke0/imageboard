package com.example.imageboard.forum;

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException; // For custom error handling

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/forums") // Updated endpoint to '/api/forums' for consistency
@RequiredArgsConstructor
public class ForumController {

    private final ForumService forumService;

    @GetMapping
    public ResponseEntity<List<ForumDto>> getAllForums() {
        return ResponseEntity.ok(forumService.getAllForums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumDto> getForumById(@PathVariable Long id) {
        ForumDto forumDto = forumService.getForumById(id);
        return ResponseEntity.ok(forumDto);
    }

    @GetMapping("/most-active")
    public ResponseEntity<Page<ForumDto>> getMostActiveForums(Pageable pageable) {
        return ResponseEntity.ok(forumService.getMostActiveForums(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @PostMapping
    public ResponseEntity<ForumDto> createForum(@Valid @RequestBody ForumDto forumDto) {
        ForumDto createdForum = forumService.createForum(forumDto);
        return ResponseEntity.created(URI.create("/api/forums/" + createdForum.getId())).body(createdForum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumDto> updateForum(@PathVariable Long id, @Valid @RequestBody ForumDto updatedForumDto) {
        try {
            ForumDto forumDto = forumService.updateForum(id, updatedForumDto);
            return ResponseEntity.ok(forumDto);
        } catch (ForumNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e); // Improved error handling
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForum(@PathVariable Long id) {
        try {
            forumService.deleteForum(id);
            return ResponseEntity.noContent().build();
        } catch (ForumNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e); // Improved error handling
        }
    }
}

