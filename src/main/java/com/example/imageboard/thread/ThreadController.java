package com.example.imageboard.thread;

import com.example.imageboard.thread.dto.ThreadDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/threads")
public class ThreadController {

    private final ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    // Endpoint to create a thread
    @PostMapping("forum/{forumId}")
    public ResponseEntity<ThreadDto> create(@PathVariable Long forumId, @RequestBody ThreadDto threadDto) {
        ThreadDto createdThread = threadService.create(forumId, threadDto);
        return ResponseEntity.created(URI.create("/api/threads/" + createdThread.getId()))
                .body(createdThread);
    }

    // Endpoint to update a thread within a specific forum
    @PutMapping("/{forumId}/{threadId}")
    public ResponseEntity<ThreadDto> update(@PathVariable Long forumId, @PathVariable Long threadId, @RequestBody ThreadDto threadDto){
        ThreadDto updatedThread = threadService.update(forumId, threadId, threadDto);
        return ResponseEntity.ok(updatedThread);
    }

    // Endpoint to delete a thread within a specific forum
    @DeleteMapping("/{forumId}/{threadId}")
    public ResponseEntity<Void> delete(@PathVariable Long forumId, @PathVariable Long threadId) {
        threadService.delete(forumId, threadId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all threads
    @GetMapping
    public ResponseEntity<List<ThreadDto>> getAll() {
        List<ThreadDto> threads = threadService.getAll();
        return ResponseEntity.ok(threads);
    }

    // Endpoint to get a thread by ID
    @GetMapping("/{id}")
    public ResponseEntity<ThreadDto> getById(@PathVariable Long id) {
        ThreadDto threadDto = threadService.getById(id);
        return ResponseEntity.ok(threadDto);
    }

    // Endpoint to get all threads by forum ID
    @GetMapping("/forum/{forumId}")
    public ResponseEntity<List<ThreadDto>> getAllByForumId(@PathVariable Long forumId) {
        List<ThreadDto> threads = threadService.getByForumId(forumId);
        return ResponseEntity.ok(threads);
    }
}

