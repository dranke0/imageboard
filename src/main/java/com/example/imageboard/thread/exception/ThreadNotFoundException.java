package com.example.imageboard.thread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Thread not found") // 404 Not Found
public class ThreadNotFoundException extends RuntimeException {

    public ThreadNotFoundException(Long id) {
        super("Thread not found with ID: " + id);
    }
}
