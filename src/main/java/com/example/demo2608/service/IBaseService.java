package com.example.demo2608.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBaseService<Entity> {

    List<Entity> findAll();

    Entity save (Entity entity);

    Optional<Entity> findById (Long id);

    void deleteById(Long id);

    Page<Entity> findAll(Pageable pageable);
}
