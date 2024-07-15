package com.example.imageboard.thread;

import com.example.imageboard.thread.dto.ThreadDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
@RequestMapping("/api/threads")
public class ThreadController {

    private final ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThreadDto> get(@PathVariable Long id) {
       threadService.get(id);
       return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ThreadDto threadDto){
        threadService.create(threadDto);
        return ResponseEntity.created(URI.create("/api/threads/")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ThreadDto threadDto){
        threadService.update(id, threadDto);
            return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            threadService.delete(id);
            return ResponseEntity.ok().build();
    }
}