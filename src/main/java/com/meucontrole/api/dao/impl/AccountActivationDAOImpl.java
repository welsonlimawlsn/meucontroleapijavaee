package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.AccountActivationDAO;
import com.meucontrole.api.entities.AccountActivation;

public class AccountActivationDAOImpl extends GenericDAOImpl<AccountActivation> implements AccountActivationDAO {

    public AccountActivationDAOImpl() {
        super(AccountActivation.class);
    }

}
