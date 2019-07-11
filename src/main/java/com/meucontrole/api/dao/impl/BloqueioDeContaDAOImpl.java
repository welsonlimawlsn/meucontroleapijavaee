package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.BloqueioDeContaDAO;
import com.meucontrole.api.entidades.BloqueioDeConta;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Optional;

public class BloqueioDeContaDAOImpl extends GenericoDAOImpl<BloqueioDeConta> implements BloqueioDeContaDAO {

    @PersistenceContext
    private EntityManager em;

    public BloqueioDeContaDAOImpl() {
        super(BloqueioDeConta.class);
    }

    @Override
    public Optional<BloqueioDeConta> procurarPorEmailDesdeAData(String email, LocalDateTime dateTime) {
        TypedQuery<BloqueioDeConta> query = em.createQuery("SELECT ab FROM BloqueioDeConta ab WHERE ab.email = :email " +
                "AND ab.expiracao > :dateTime", BloqueioDeConta.class);
        query.setParameter("email", email);
        query.setParameter("dateTime", dateTime);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
