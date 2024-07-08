package com.example.imageboard.entity;

import com.example.imageboard.error.ErrorResponseDto;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;
import java.util.List;

public interface EntityController<T extends EntityModel, ID extends Serializable, D extends EntityDto, S extends EntityService<T, ID, D>> {
    ResponseEntity<ErrorResponseDto> handleEntityNotFound(EntityNotFoundException ex, WebRequest request);
    ResponseEntity<ErrorResponseDto> handleInvalidEntity(InvalidEntityException ex, WebRequest request);

    // Generic methods for CRUD operations
    List<D> getAll();
    D getById(@PathVariable ID id);
    public D create(@RequestBody D dto);
    public D update(@PathVariable ID id, @RequestBody D dto);
    public void delete(@PathVariable ID id);
}


