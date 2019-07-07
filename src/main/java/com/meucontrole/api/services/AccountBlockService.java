package com.meucontrole.api.services;

import com.meucontrole.api.dao.AccountBlockDAO;
import com.meucontrole.api.entities.AccountBlock;
import com.meucontrole.api.util.ApplicationDate;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AccountBlockService {

    @Inject
    private AccountBlockDAO dao;

    public void block(String email) {
        AccountBlock accountBlock = new AccountBlock();
        accountBlock.setEmail(email);
        accountBlock.setExpiration(ApplicationDate.localDateTimeNow().plusMinutes(30));
        dao.insert(accountBlock);
    }

    public boolean isBlocked(String email) {
        return dao.findByEmailAndSinceDateTime(email, ApplicationDate.localDateTimeNow()).isPresent();
    }
}
