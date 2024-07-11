package com.example.imageboard.forum; // Adjust the package if needed

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import com.example.imageboard.forum.exception.InvalidForumException;
import com.example.imageboard.forum.validator.ForumValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forums")
@RequiredArgsConstructor
public class ForumController {

    private final ForumService forumService;
    private final ForumValidator forumValidator; // Assuming you have a ForumValidator class

    @GetMapping
    public ResponseEntity<List<ForumDto>> getAllForums(@RequestParam(required = false) boolean withThreads) {
        if (withThreads) {
            return ResponseEntity.ok(forumService.getAllForumsWithThreads());
        } else {
            return ResponseEntity.ok(forumService.getAllForums());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumDto> getForumById(@PathVariable Long id) {
        Optional<ForumDto> forumDto = Optional.ofNullable(forumService.getForumById(id)); // Use Optional<ForumDto>
        return forumDto.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found with ID: " + id));
    }

    @GetMapping("/most-active")
    public ResponseEntity<List<ForumDto>> getMostActiveForums(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(forumService.getMostActiveForums(limit));
    }

    @PostMapping
    public ResponseEntity<ForumDto> createForum(@Valid @RequestBody ForumDto forumDto, BindingResult bindingResult) {
        forumValidator.validate(forumDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new InvalidForumException(bindingResult);
        }

        ForumDto createdForum = forumService.createForum(forumDto);
        return ResponseEntity.created(URI.create("/api/forums/" + createdForum.getId())).body(createdForum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumDto> updateForum(@PathVariable Long id, @Valid @RequestBody ForumDto updatedForumDto, BindingResult bindingResult) {
        forumValidator.validate(updatedForumDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new InvalidForumException(bindingResult);
        }
        ForumDto forumDto = forumService.updateForum(id, updatedForumDto);
        return ResponseEntity.ok(forumDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForum(@PathVariable Long id) {
        forumService.deleteForum(id);
        return ResponseEntity.noContent().build();
    }

    // Global Exception Handler (Example)
    @ExceptionHandler({ForumNotFoundException.class, InvalidForumException.class})
    public ResponseEntity<?> handleForumExceptions(RuntimeException ex) {
        if (ex instanceof ForumNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } else if (ex instanceof InvalidForumException) {
            return ResponseEntity.badRequest().body(((InvalidForumException) ex).getErrors());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}







