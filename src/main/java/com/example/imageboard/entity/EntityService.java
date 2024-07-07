package com.example.imageboard.entity;

import com.example.imageboard.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public abstract class EntityService<T extends BaseEntity, ID extends Serializable, D extends EntityDto> {

    private final EntityRepository<T, ID> repository;
    private final EntityValidator entityValidator;
    private final EntityMapper<T, D> entityMapper; // Specific mapper for this service

    @Transactional(readOnly = true)
    public List<D> findAllDtos() {
        log.debug("Fetching all DTOs of type: {}", entityClass());
        List<T> entities = repository.findAll();
        return entityMapper.mapEntitiesToDtos(entities);
    }

    @Transactional(readOnly = true)
    public D findByIdDto(ID id) {
        log.debug("Fetching DTO of type: {} with id: {}", entityClass(), id);
        return entityMapper.mapEntityToDto(findOrThrow(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public D save(D dto) {
        log.debug("Saving entity from DTO of type: {}: {}", entityClass(), dto);
        T entity = entityMapper.mapDtoToEntity(dto);
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(entity, entityClass().getSimpleName());
        entityValidator.validate(entity, errors);
        if (errors.hasErrors()) {
            throw new InvalidEntityException(errors.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        T savedEntity = repository.save(entity);
        return entityMapper.mapEntityToDto(savedEntity);
    }

    @Transactional
    public void delete(ID id) {
        log.debug("Deleting entity of type: {} with id: {}", entityClass(), id);
        repository.deleteById(id);
    }

    @Transactional(readOnly = true) // Added transactional annotation for potential performance benefit
    public T findOrThrow(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    private Class<T> entityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}


