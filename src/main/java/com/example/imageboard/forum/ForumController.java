package com.example.imageboard.forum; // Adjust the package if needed

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/forums")
@RequiredArgsConstructor
public class ForumController {

    private final ForumService forumService;
    @GetMapping
    public ResponseEntity<List<ForumDto>> getAllForums(@RequestParam(required = false) boolean withThreads) {
        if (withThreads) {
            return ResponseEntity.ok(forumService.getAllForumsWithThreads());
        } else {
            return ResponseEntity.ok(forumService.getAllForums());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumDto> getForumById(@PathVariable Long id) {
        return forumService.getForumById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found with ID: " + id));
    }

    @GetMapping("/most-active")
    public ResponseEntity<List<ForumDto>> getMostActiveForums(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(forumService.getMostActiveForums(limit));
    }

    @PostMapping
    public ResponseEntity<ForumDto> createForum(@Valid @RequestBody ForumDto forumDto) {
        ForumDto createdForum = forumService.createForum(forumDto);
        return ResponseEntity.created(URI.create("/api/forums/" + createdForum.getId())).body(createdForum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumDto> updateForum(@PathVariable Long id, @Valid @RequestBody ForumDto forumDto) {
        ForumDto updatedForum = forumService.updateForum(id, forumDto);
        return ResponseEntity.ok(updatedForum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForum(@PathVariable Long id) {
        forumService.deleteForum(id);
        return ResponseEntity.noContent().build();
    }
}




