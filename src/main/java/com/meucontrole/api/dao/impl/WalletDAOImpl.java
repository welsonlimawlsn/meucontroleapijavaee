package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.WalletDAO;
import com.meucontrole.api.entities.User;
import com.meucontrole.api.entities.Wallet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class WalletDAOImpl extends GenericDAOImpl<Wallet> implements WalletDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public WalletDAOImpl() {
        super(Wallet.class);
    }

    @Override
    public Optional<Wallet> findByUser(User user) {
        TypedQuery<Wallet> query = entityManager.createQuery("SELECT w FROM Wallet w WHERE w.user = :user", Wallet.class);
        query.setParameter("user", user);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
