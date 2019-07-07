package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.AttemptSignInDAO;
import com.meucontrole.api.entities.AttemptSignIn;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;

public class AttemptSignInDAOImpl extends GenericDAOImpl<AttemptSignIn> implements AttemptSignInDAO {

    @PersistenceContext
    private EntityManager em;

    public AttemptSignInDAOImpl() {
        super(AttemptSignIn.class);
    }

    @Override
    public long getNumberOfAttemptByEmailAndSinceDateTime(String email, LocalDateTime dateTime) {
        TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM AttemptSignIn asi WHERE asi.email = :email " +
                "AND asi.dateTime > :dateTime", Long.class);
        query.setParameter("email", email);
        query.setParameter("dateTime", dateTime);
        return query.getSingleResult();
    }

}
