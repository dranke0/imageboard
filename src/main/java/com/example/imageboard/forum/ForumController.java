package com.example.imageboard.forum;

import com.example.imageboard.base.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Slf4j
public class ForumController {

    private final ForumService forumService;

    @GetMapping
    public List<ForumDto> getAllBoards() {
        log.info("Fetching all boards");
        return forumService.getAllBoards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumDto> getBoardById(@PathVariable Long id) {
        ForumDto forumDto = forumService.getBoardById(id);
        if (forumDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(forumDto);
    }

    @PostMapping
    public ResponseEntity<ForumDto> createBoard(@Valid @RequestBody ForumDto forumDto) {
        ForumDto createdBoard = forumService.createBoard(forumDto);
        return ResponseEntity.created(URI.create("/api/boards/" + createdBoard.getId())).body(createdBoard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumDto> updateBoard(@PathVariable Long id, @Valid @RequestBody ForumDto updatedForumDto) {
        try {
            ForumDto forumDto = forumService.updateBoard(id, updatedForumDto);
            return ResponseEntity.ok(forumDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        try {
        forumService.deleteBoard(id);
        return ResponseEntity.noContent().build(); // Correct HTTP status code
    } catch (ResourceNotFoundException e) {
        return ResponseEntity.notFound().build();}
    }
}
