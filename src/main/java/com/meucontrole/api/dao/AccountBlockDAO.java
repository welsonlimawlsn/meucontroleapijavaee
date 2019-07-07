package com.meucontrole.api.dao;

import com.meucontrole.api.entities.AccountBlock;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AccountBlockDAO extends GenericDAO<AccountBlock> {

    Optional<AccountBlock> findByEmailAndSinceDateTime(String email, LocalDateTime dateTime);
}
