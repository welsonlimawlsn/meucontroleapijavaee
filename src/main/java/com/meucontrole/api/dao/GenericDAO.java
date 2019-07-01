package com.meucontrole.api.dao;

import com.meucontrole.api.entities.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<E extends AbstractEntity> {

    Optional<E> findById(String id);

    E insert(E entity);

    E update(E entity);

    void remove(E entity);

    List<E> listAll();
}
