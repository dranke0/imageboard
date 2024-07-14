package com.example.imageboard.forum; // Adjust the package if needed

import com.example.imageboard.forum.dto.ForumDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/forums")
public class ForumController {

    private final ForumService forumService;

    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ForumDto> getForumById(@PathVariable Long id) {
        Optional<ForumDto> forumDto = Optional.ofNullable(forumService.get(id));
        return forumDto.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found with ID: " + id));
    }

    @PostMapping
    public ResponseEntity<ForumDto> create(@RequestBody ForumDto forumDto){
        forumService.create(forumDto);
        return ResponseEntity.created(URI.create("/api/threads/")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumDto> update(@PathVariable Long id, @RequestBody ForumDto updatedForumDto, BindingResult bindingResult) {
        forumService.update(id, updatedForumDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        forumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}







