package com.example.imageboard.thread;

import com.example.imageboard.thread.dto.ThreadDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/threads/")
public class ThreadController {

    private final ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThreadDto> get(@PathVariable Long id) {
      ThreadDto threadDto = threadService.get(id);
       return ResponseEntity.ok(threadDto);
    }


    @PostMapping
    public ResponseEntity<ThreadDto> create(@RequestBody ThreadDto threadDto){
        ThreadDto createdThread = threadService.create(threadDto);
        return ResponseEntity.created(URI.create("/api/threads/" + createdThread.getId()))
                .body(createdThread);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThreadDto> update(@PathVariable Long id, @RequestBody ThreadDto threadDto, @RequestParam String password){
        ThreadDto updatedThread = threadService.update(id, threadDto, password);
            return ResponseEntity.ok(updatedThread);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam String password) {
            threadService.delete(id, password);
            return ResponseEntity.noContent().build();
    }
}
