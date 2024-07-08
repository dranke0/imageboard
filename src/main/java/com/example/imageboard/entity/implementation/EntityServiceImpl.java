package com.example.imageboard.entity.implementation;

import com.example.imageboard.entity.EntityDto;
import com.example.imageboard.entity.EntityModel;
import com.example.imageboard.entity.EntityRepository;
import com.example.imageboard.entity.EntityService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntityServiceImpl implements EntityService<EntityModelImpl, Long, EntityDtoImpl> {

    private final EntityRepositoryImpl repository;
    private final ModelMapper modelMapper;
    private final EntityMapperImpl entityMapper;

    public EntityServiceImpl(EntityRepositoryImpl repository, ModelMapper modelMapper, EntityMapperImpl entityMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<EntityDtoImpl> findAll() {
        return repository.findAll().stream()
                .map(entityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EntityDtoImpl findById(Long id) {
        return repository.findById(id)
                .map(entityMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    @Override
    public EntityDtoImpl create(EntityDtoImpl dto) {
        EntityModelImpl model = entityMapper.toModel(dto);
        EntityModelImpl savedModel = repository.save(model);
        return entityMapper.toDto(savedModel);
    }

    @Override
    public EntityDtoImpl create(EntityModelImpl model) {
        EntityModelImpl savedModel = repository.save(model);
        return entityMapper.toDto(savedModel);
    }

    @Override
    public EntityDtoImpl update(EntityDtoImpl dto) {
        EntityModelImpl model = entityMapper.toModel(dto);
        if (model.getId() == null) {
            throw new IllegalArgumentException("Entity ID is required for update");
        }
        EntityModelImpl savedModel = repository.save(model);
        return entityMapper.toDto(savedModel);
    }

    @Override
    public EntityDtoImpl update(EntityModelImpl model) {
        if (model.getId() == null) {
            throw new IllegalArgumentException("Entity ID is required for update");
        }
        EntityModelImpl savedModel = repository.save(model);
        return entityMapper.toDto(savedModel);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public EntityModelImpl findOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }
}




