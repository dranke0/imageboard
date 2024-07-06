package com.example.imageboard.entity;

import java.util.List;
import java.util.Optional;

public interface EntityFacade<E, D> {
    D mapEntityToDto(E entity);
    E mapDtoToEntity(D dto);
    List<D> mapEntitiesToDtos(List<E> entities);
    List<E> mapDtosToEntities(List<D> dtos);
    Optional<D> mapOptionalEntityToDto(Optional<E> entity);
    Optional<E> mapOptionalDtoToEntity(Optional<D> dto);
}