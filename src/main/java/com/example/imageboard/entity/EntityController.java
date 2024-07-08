package com.example.imageboard.entity;

import com.example.imageboard.error.ErrorResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface EntityController<T extends EntityModel, D extends EntityDto> {
    ResponseEntity<ErrorResponseDto> handleEntityNotFound(EntityNotFoundException ex, WebRequest request); // Only one error handler

    List<D> getAll();
    D getById(@PathVariable Long id);
    ResponseEntity<D> create(@RequestBody D dto);
    D update(@PathVariable Long id, @RequestBody D dto);
    ResponseEntity<Void> delete(@PathVariable Long id);
}



