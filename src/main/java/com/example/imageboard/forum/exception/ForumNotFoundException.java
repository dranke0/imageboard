package com.example.imageboard.forum.exception; // Make sure this is in your correct package

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Forum not found")
public class ForumNotFoundException extends RuntimeException {

    public ForumNotFoundException(Long id) {
        super("Forum not found with ID: " + id);
    }
}


