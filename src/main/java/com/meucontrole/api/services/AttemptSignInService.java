package com.meucontrole.api.services;

import com.meucontrole.api.dao.AttemptSignInDAO;
import com.meucontrole.api.entities.AttemptSignIn;
import com.meucontrole.api.util.ApplicationDate;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AttemptSignInService {

    @Inject
    private AttemptSignInDAO dao;

    @EJB
    private AccountBlockService accountBlockService;

    public void registerAttempt(String email) {
        AttemptSignIn attemptSignIn = new AttemptSignIn();
        attemptSignIn.setEmail(email);
        attemptSignIn.setDateTime(ApplicationDate.localDateTimeNow());
        dao.insert(attemptSignIn);
    }

    public void verifyNumberOfAttemptsAndBlockIfNecessary(String email) {
        long numberOfAttemptByEmailAndSinceDateTime = dao.getNumberOfAttemptByEmailAndSinceDateTime(email,
                ApplicationDate.localDateTimeNow().minusMinutes(1));
        if (numberOfAttemptByEmailAndSinceDateTime > 3) {
            accountBlockService.block(email);
        }
    }
}
