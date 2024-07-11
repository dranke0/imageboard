package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.comment.validator.CommentDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")  // Base path for comment endpoints
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentDtoValidator commentDtoValidator;

    @GetMapping
    public ResponseEntity<Page<CommentDto>> getAllComments(Pageable pageable) {
        Page<CommentDto> comments = commentService.getAllComments(pageable);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult,
                                                    @RequestParam Long threadId, @RequestParam Long userId) {
        commentDtoValidator.validate(commentDto, bindingResult);
        if (bindingResult.hasErrors()) {
            // Handle validation errors (e.g., return them in the response)
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        CommentDto createdComment = commentService.createComment(commentDto, threadId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @Valid @RequestBody CommentDto updatedCommentDto) {
        CommentDto comment = commentService.updateComment(id, updatedCommentDto);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    // ... other endpoints for filtering, searching, etc.
}


