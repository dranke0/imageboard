package com.example.imageboard.thread;

import com.example.imageboard.thread.ThreadService;
import com.example.imageboard.thread.dto.ThreadDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/forums/{forumId}/threads")
public class ThreadController {

    private final ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    // Endpoint to create a thread within a specific forum
    @PostMapping
    public ResponseEntity<ThreadDto> create(@PathVariable Long forumId, @RequestBody ThreadDto threadDto) {
        ThreadDto createdThread = threadService.create(forumId, threadDto);
        return ResponseEntity.created(URI.create("/api/forums/" + forumId + "/threads/" + createdThread.getId()))
                .body(createdThread);
    }

    // Endpoint to update a thread within a specific forum
    @PutMapping("/{threadId}")
    public ResponseEntity<ThreadDto> update(@PathVariable Long forumId, @PathVariable Long threadId, @RequestBody ThreadDto threadDto) {
        ThreadDto updatedThread = threadService.update(forumId, threadId, threadDto);
        return ResponseEntity.ok(updatedThread);
    }

    // Endpoint to delete a thread within a specific forum
    @DeleteMapping("/{threadId}")
    public ResponseEntity<Void> delete(@PathVariable Long forumId, @PathVariable Long threadId) {
        threadService.delete(forumId, threadId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all threads within a specific forum
    @GetMapping
    public ResponseEntity<List<ThreadDto>> getAll(@PathVariable Long forumId) {
        List<ThreadDto> threads = threadService.getByForumId(forumId);
        return ResponseEntity.ok(threads);
    }

    // Endpoint to get a thread by ID within a specific forum
    @GetMapping("/{threadId}")
    public ResponseEntity<ThreadDto> getById(@PathVariable Long forumId, @PathVariable Long threadId) {
        ThreadDto threadDto = threadService.getById(threadId);
        return ResponseEntity.ok(threadDto);
    }
}
