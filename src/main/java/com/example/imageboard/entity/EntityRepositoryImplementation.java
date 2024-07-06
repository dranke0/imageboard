package com.example.imageboard.entity;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public class EntityRepositoryImplementation<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements EntityRepository<T, ID> {

    private final EntityManager entityManager;

    private final Class<T> domainClass;

    public EntityRepositoryImplementation(EntityManager entityManager, Class<T> domainClass) {
        super(JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager), entityManager);
        this.entityManager = entityManager;
        this.domainClass = domainClass;
    }
}
