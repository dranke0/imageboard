package com.example.imageboard.entity;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface EntityService<T extends EntityModel, D extends EntityDto> {
    List<D> findAll();
    D findById(Long id) throws EntityNotFoundException;
    D create(D dto);
    D update(Long id, D dto);
    void delete(Long id);
}
