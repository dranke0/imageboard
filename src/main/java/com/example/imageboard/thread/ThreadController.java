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
    @PostMapping("/{forumId}")
    public ResponseEntity<ThreadDto> createThread(@PathVariable Long forumId, @RequestBody ThreadDto threadDto) {
        ThreadDto createdThread = threadService.createThread(forumId, threadDto);
        return ResponseEntity.created(URI.create("/api/threads/" + createdThread.getId()))
                .body(createdThread);
    }

    // Endpoint to update a thread within a specific forum
    @PutMapping("/{forumId}/{threadId}")
    public ResponseEntity<ThreadDto> updateThread(@PathVariable Long forumId, @PathVariable Long threadId, @RequestBody ThreadDto threadDto){
        ThreadDto updatedThread = threadService.updateThread(forumId, threadId, threadDto);
        return ResponseEntity.ok(updatedThread);
    }

    // Endpoint to delete a thread within a specific forum
    @DeleteMapping("/{forumId}/{threadId}")
    public ResponseEntity<Void> deleteThread(@PathVariable Long forumId, @PathVariable Long threadId) {
        threadService.deleteThread(forumId, threadId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all threads
    @GetMapping
    public ResponseEntity<List<ThreadDto>> getAllThreads() {
        List<ThreadDto> threads = threadService.getAllThreads();
        return ResponseEntity.ok(threads);
    }

    // Endpoint to get a thread by ID
    @GetMapping("/{id}")
    public ResponseEntity<ThreadDto> getThreadById(@PathVariable Long id) {
        ThreadDto threadDto = threadService.getThreadById(id);
        return ResponseEntity.ok(threadDto);
    }

    // Endpoint to get all threads by forum ID
    @GetMapping("/forum/{forumId}")
    public ResponseEntity<List<ThreadDto>> getAllThreadsByForumId(@PathVariable Long forumId) {
        List<ThreadDto> threads = threadService.getThreadsByForumId(forumId);
        return ResponseEntity.ok(threads);
    }

}

