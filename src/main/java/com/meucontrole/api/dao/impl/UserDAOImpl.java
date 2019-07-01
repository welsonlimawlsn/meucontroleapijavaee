package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.UserDAO;
import com.meucontrole.api.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return Optional.of(query.getSingleResult());
    }
}
