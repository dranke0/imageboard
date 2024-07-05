package com.example.imageboard.forumThread;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ... (similar structure to ForumController)
@RestController
@RequestMapping("/api/threads")
public class ForumThreadController {
    // ... inject ForumThreadService
    // ... methods for get all forumThreads, get by id, create, update, delete
}
