package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.ApplicationUserDAO;
import com.meucontrole.api.entities.ApplicationUser;
import com.meucontrole.api.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class ApplicationUserDAOImpl extends GenericDAOImpl<ApplicationUser> implements ApplicationUserDAO {

    @PersistenceContext
    private EntityManager em;

    public ApplicationUserDAOImpl() {
        super(ApplicationUser.class);
    }

    @Override
    public Optional<ApplicationUser> findByEmailAndPassword(String email, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return Optional.of(query.getSingleResult());
    }

    @Override
    public Optional<ApplicationUser> findByEmail(String email) {
        TypedQuery<ApplicationUser> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", ApplicationUser.class);
        query.setParameter("email", email);
        try {
            ApplicationUser result = query.getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
