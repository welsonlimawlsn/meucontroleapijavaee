package com.meucontrole.api.services;

import com.meucontrole.api.dao.AccountActivationDAO;
import com.meucontrole.api.entities.AccountActivation;
import com.meucontrole.api.entities.ApplicationUser;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AccountActivationService {

    @Inject
    private AccountActivationDAO dao;

    public void sendLinkForActivationOfAccount(ApplicationUser applicationUser) {
        AccountActivation accountActivation = new AccountActivation(applicationUser);
        dao.insert(accountActivation);
    }
}
