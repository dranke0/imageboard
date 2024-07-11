package com.example.imageboard.comment; // Adjust the package if needed

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.comment.exception.CommentNotFoundException;
import com.example.imageboard.comment.exception.InvalidCommentException;
import com.example.imageboard.comment.validator.CommentDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentDtoValidator commentDtoValidator;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        Optional<CommentDto> optionalCommentDto = Optional.ofNullable(commentService.getCommentById(id)); // Use Optional<CommentDto>
        return optionalCommentDto.map(ResponseEntity::ok)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult,
                                                    @RequestParam Long threadId, @RequestParam Long userId) {

        commentDtoValidator.validate(commentDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new InvalidCommentException(bindingResult);
        }

        CommentDto createdComment = commentService.createComment(commentDto, threadId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @Valid @RequestBody CommentDto updatedCommentDto, BindingResult bindingResult) {
        commentDtoValidator.validate(updatedCommentDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new InvalidCommentException(bindingResult);
        }
        CommentDto updatedComment = commentService.updateComment(id, updatedCommentDto);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}



