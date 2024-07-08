package com.example.imageboard.entity;

import java.util.List;

public interface EntityMapper<E, D> {
    D mapEntityToDto(E entity);
    E mapDtoToEntity(D dto);
    List<D> mapEntitiesToDtos(List<E> entities);
    List<E> mapDtosToEntities(List<D> dtos);
}


