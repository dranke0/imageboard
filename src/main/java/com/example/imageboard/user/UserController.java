package com.example.imageboard.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ... (similar structure to ForumController)
@RestController
@RequestMapping("/api/users")
public class UserController {
    // ... inject UserService
    // ... methods for get all users, get by id, create, update (maybe not delete)
}
