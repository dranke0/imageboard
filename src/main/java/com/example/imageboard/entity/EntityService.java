package com.example.imageboard.entity;

import jakarta.persistence.EntityNotFoundException;

import java.io.Serializable;
import java.util.List;
public interface EntityService<T extends EntityModel, ID extends Serializable, D extends EntityDto> {
    List<D> findAll();
    D findById(ID id) throws EntityNotFoundException;
    D create(D dto);
    D create(T model);
    D update(D dto);
    D update(T model);
    void delete(ID id);
    T findOrThrow(ID id);
}


