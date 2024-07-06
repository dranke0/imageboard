package com.example.imageboard.entity;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor // Lombok will create constructor
public abstract class EntityService<T extends EntityModel, ID extends Serializable> {

    protected final EntityRepository<T, ID> repository; // Lombok will inject this dependency

    @Transactional(readOnly = true)
    public List<T> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Transactional
    public void delete(ID id) {
        repository.deleteById(id);
    }

    public T findOrThrow(ID id) {
        return getById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }
}


