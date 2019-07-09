package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.PasswordRecoveryDAO;
import com.meucontrole.api.entities.PasswordRecovery;
import com.meucontrole.api.util.ApplicationDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class PasswordRecoveryDAOImpl extends GenericDAOImpl<PasswordRecovery> implements PasswordRecoveryDAO {

    @PersistenceContext
    private EntityManager em;

    public PasswordRecoveryDAOImpl() {
        super(PasswordRecovery.class);
    }

    @Override
    public Optional<PasswordRecovery> findByIdAndValidExpiration(String id) {
        TypedQuery<PasswordRecovery> query = em.createQuery("SELECT pr FROM PasswordRecovery pr WHERE pr.id = :id" +
                " AND pr.expiration > :datetime", PasswordRecovery.class);
        query.setParameter("id", id);
        query.setParameter("datetime", ApplicationDate.localDateTimeNow());
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
