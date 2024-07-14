package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.comment.exception.CommentNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
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
        Optional<CommentDto> optionalCommentDto = Optional.ofNullable(commentService.getById(id));
        return optionalCommentDto.map(ResponseEntity::ok)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CommentDto commentDto, @RequestParam Long threadId) {
        commentService.create(commentDto);
        return ResponseEntity.created(URI.create("/api/comments/")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        commentService.update(id, commentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}



