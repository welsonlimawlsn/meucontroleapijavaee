package com.meucontrole.api.dao;

import com.meucontrole.api.entities.PasswordRecovery;

import java.util.Optional;

public interface PasswordRecoveryDAO extends GenericDAO<PasswordRecovery> {

    Optional<PasswordRecovery> findByIdAndValidExpiration(String id);
}
