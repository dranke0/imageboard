package com.example.imageboard.forum; // Adjust the package if needed

import com.example.imageboard.forum.dto.ForumDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/forums/")
public class ForumController {

    private final ForumService forumService;

    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @GetMapping
    public ResponseEntity<List<ForumDto>> getAll() {
        List<ForumDto> forumDtos = forumService.getAll();
        return ResponseEntity.ok(forumDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ForumDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(forumService.get(id));
    }

    @PostMapping
    public ResponseEntity<ForumDto> create(@RequestBody ForumDto forumDto) {
        ForumDto createdForum = forumService.create(forumDto);
        return ResponseEntity.created(URI.create("/api/forums/" + createdForum.getId())).body(createdForum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumDto> update(@PathVariable Long id, @RequestBody ForumDto updatedForumDto) {
        ForumDto updatedForum = forumService.update(id, updatedForumDto);
        return ResponseEntity.ok(updatedForum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        forumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}








