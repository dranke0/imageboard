package com.example.imageboard.entity.implementation;

import com.example.imageboard.entity.EntityRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public class EntityRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements EntityRepository<T, ID> {

    @Autowired // Inject EntityManager
    private final EntityManager entityManager;
    private final JpaEntityInformation<T, ?> entityInformation;
    public EntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }
}
