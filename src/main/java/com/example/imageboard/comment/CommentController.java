package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/forums/{forumId}/threads/{threadId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(
            @PathVariable Long threadId,
            @RequestBody CommentDto commentDto) {

        CommentDto createdComment = commentService.create(threadId, commentDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{commentId}")
                .buildAndExpand(createdComment.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdComment);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getComment(
            @PathVariable Long threadId,
            @PathVariable Long commentId) {

        CommentDto commentDto = commentService.findById(commentId);
        if (commentDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commentDto);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllCommentsForThread(
            @PathVariable Long threadId) {

        List<CommentDto> comments = commentService.findAllByThreadId(threadId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentDto commentDto) {

        CommentDto updatedComment = commentService.update(commentId, commentDto);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId) {

        commentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }
}





