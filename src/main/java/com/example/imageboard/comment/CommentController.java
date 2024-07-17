package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAll() {
        List<CommentDto> comments = commentService.getAll();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getById(@PathVariable Long id) {
        CommentDto commentDto = commentService.getById(id);
        return ResponseEntity.ok(commentDto);
    }

    @PostMapping("/{threadId}")
    public ResponseEntity<CommentDto> create(@PathVariable Long threadId, @RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.create(threadId, commentDto);
        return ResponseEntity.created(URI.create("/api/comments/" + createdComment.getId()))
                .body(createdComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        commentService.update(id, commentDto);
        return ResponseEntity.ok(commentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}




