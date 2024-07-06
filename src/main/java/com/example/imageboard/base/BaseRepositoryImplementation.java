package com.example.imageboard.base;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    private final Class<T> domainClass;

    public BaseRepositoryImpl(EntityManager entityManager, Class<T> domainClass) {
        super(JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager), entityManager);
        this.entityManager = entityManager;
        this.domainClass = domainClass;
    }
}
