package com.example.imageboard.entity;

import com.example.imageboard.error.ErrorCode;
import com.example.imageboard.error.ErrorResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class EntityController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        log.error("Entity not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ErrorCode.ENTITY_NOT_FOUND.toString(), ex.getMessage(), List.of(), LocalDateTime.now(), request.getDescription(false)));
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidEntity(InvalidEntityException ex, WebRequest request) {
        log.error("Invalid entity: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ErrorCode.INVALID_ENTITY.toString(), "Validation failed", ex.getValidationErrors(), LocalDateTime.now(), request.getDescription(false)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex, WebRequest request) {
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCode.INTERNAL_SERVER_ERROR.toString(), "An unexpected error occurred", null, LocalDateTime.now(), request.getDescription(false)));
    }
}

