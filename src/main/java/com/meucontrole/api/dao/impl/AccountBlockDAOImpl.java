package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.AccountBlockDAO;
import com.meucontrole.api.entities.AccountBlock;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Optional;

public class AccountBlockDAOImpl extends GenericDAOImpl<AccountBlock> implements AccountBlockDAO {

    @PersistenceContext
    private EntityManager em;

    public AccountBlockDAOImpl() {
        super(AccountBlock.class);
    }

    @Override
    public Optional<AccountBlock> findByEmailAndSinceDateTime(String email, LocalDateTime dateTime) {
        TypedQuery<AccountBlock> query = em.createQuery("SELECT ab FROM AccountBlock ab WHERE ab.email = :email " +
                "AND ab.expiration > :dateTime", AccountBlock.class);
        query.setParameter("email", email);
        query.setParameter("dateTime", dateTime);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
