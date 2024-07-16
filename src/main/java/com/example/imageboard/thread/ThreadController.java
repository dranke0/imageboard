package com.example.imageboard.thread;

import com.example.imageboard.thread.dto.ThreadDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/threads/")
public class ThreadController {

    private final ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping
    public ResponseEntity<List<ThreadDto>> getAllThreads() {
        List<ThreadDto> threads = threadService.getAllThreads();
        return ResponseEntity.ok(threads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThreadDto> getThreadById(@PathVariable Long id) {
        ThreadDto threadDto = threadService.getThreadById(id);
        return ResponseEntity.ok(threadDto);
    }

    @GetMapping
    public ResponseEntity<List<ThreadDto>> getAllThreadsByForumId(@RequestParam(required = false) Long forumId) {
        List<ThreadDto> threads = forumId != null
                ? threadService.getThreadsByForumId(forumId)
                : threadService.getAllThreads();
        return ResponseEntity.ok(threads);
    }

    @PostMapping
    public ResponseEntity<ThreadDto> createThread(@RequestBody ThreadDto threadDto) {
        ThreadDto createdThread = threadService.createThread(threadDto);
        return ResponseEntity.created(URI.create("/api/threads/" + createdThread.getId()))
                .body(createdThread);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThreadDto> updateThread(@PathVariable Long id, @RequestBody ThreadDto threadDto){
        ThreadDto updatedThread = threadService.updateThread(id, threadDto);
        return ResponseEntity.ok(updatedThread);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThread(@PathVariable Long id) {
        threadService.deleteThread(id);
        return ResponseEntity.noContent().build();
    }
}
