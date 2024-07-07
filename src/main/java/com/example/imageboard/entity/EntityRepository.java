package com.example.imageboard.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface EntityRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
