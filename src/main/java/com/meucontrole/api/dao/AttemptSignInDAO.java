package com.meucontrole.api.dao;

import com.meucontrole.api.entities.AttemptSignIn;

import java.time.LocalDateTime;

public interface AttemptSignInDAO extends GenericDAO<AttemptSignIn> {

    long getNumberOfAttemptByEmailAndSinceDateTime(String email, LocalDateTime dateTime);
}
