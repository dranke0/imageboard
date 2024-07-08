package com.example.imageboard.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface EntityRepository<T extends EntityModel, ID extends Serializable> extends JpaRepository<T, ID> {
}
