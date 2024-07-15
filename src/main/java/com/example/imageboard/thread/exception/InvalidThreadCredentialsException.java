package com.example.imageboard.thread.exception; // Place it in your exceptions package

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid thread credentials") // 401 Unauthorized
public class InvalidThreadCredentialsException extends RuntimeException {

    public InvalidThreadCredentialsException() {
        super("Invalid thread credentials");
    }

    public InvalidThreadCredentialsException(String message) {
        super(message);
    }
}
