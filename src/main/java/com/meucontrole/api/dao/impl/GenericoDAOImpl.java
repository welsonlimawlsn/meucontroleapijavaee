package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.GenericoDAO;
import com.meucontrole.api.entidades.EntidadeAbstrata;
import com.meucontrole.api.util.GeradorDeId;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class GenericoDAOImpl<E extends EntidadeAbstrata> implements GenericoDAO<E> {

    @PersistenceContext
    private EntityManager em;
    private Class<E> aClass;

    public GenericoDAOImpl(Class<E> aClass) {
        this.aClass = aClass;
    }

    @Override
    public Optional<E> procurarPorId(String id) {
        return Optional.of(em.find(aClass, id));
    }

    @Override
    public E inserir(E entidade) {
        try {
            String id = GeradorDeId.getInstance().gerar();
            entidade.setId(id);
            em.persist(entidade);
            return entidade;
        } catch (EntityExistsException e) {
            return inserir(entidade);
        }
    }

    @Override
    public E alterar(E entidade) {
        em.merge(entidade);
        return entidade;
    }

    @Override
    public void remover(E entidade) {
        em.remove(entidade);
    }

    @Override
    public List<E> listarTodos() {
        return em.createQuery("SELECT e FROM " + aClass.getSimpleName() + " e", aClass).getResultList();
    }

}
