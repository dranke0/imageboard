package com.example.imageboard.comment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ... (similar structure to ForumController)
@RestController
@RequestMapping("/api/posts")
public class CommentController {
    // ... inject CommentService
    // ... methods for get all comments, get by id, create, update, delete
}

