package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.GenericDAO;
import com.meucontrole.api.entities.AbstractEntity;
import com.meucontrole.api.util.IdGenerator;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class GenericDAOImpl<E extends AbstractEntity> implements GenericDAO<E> {

    @PersistenceContext
    private EntityManager em;
    private Class<E> aClass;

    public GenericDAOImpl(Class<E> aClass) {
        this.aClass = aClass;
    }

    @Override
    public Optional<E> findById(String id) {
        return Optional.of(em.find(aClass, id));
    }

    @Override
    public E insert(E entity) {
        try {
            String id = IdGenerator.getInstance().generate();
            entity.setId(id);
            em.persist(entity);
            return entity;
        } catch (EntityExistsException e) {
            return insert(entity);
        }
    }

    @Override
    public E update(E entity) {
        em.merge(entity);
        return entity;
    }

    @Override
    public void remove(E entity) {
        em.remove(entity);
    }

    @Override
    public List<E> listAll() {
        return em.createQuery("SELECT e FROM " + aClass.getSimpleName() + " e", aClass).getResultList();
    }

}
