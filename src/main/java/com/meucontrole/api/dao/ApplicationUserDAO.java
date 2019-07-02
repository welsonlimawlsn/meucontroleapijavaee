package com.meucontrole.api.dao;

import com.meucontrole.api.entities.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDAO extends GenericDAO<ApplicationUser> {

    Optional<ApplicationUser> findByEmailAndPassword(String email, String password);

    Optional<ApplicationUser> findByEmail(String email);
}
