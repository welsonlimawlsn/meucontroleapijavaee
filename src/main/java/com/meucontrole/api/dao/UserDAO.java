package com.meucontrole.api.dao;

import com.meucontrole.api.entities.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByEmailAndPassword(String email, String password);
}
