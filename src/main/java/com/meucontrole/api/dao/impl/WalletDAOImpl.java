package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.WalletDAO;
import com.meucontrole.api.entities.Wallet;

public class WalletDAOImpl extends GenericDAOImpl<Wallet> implements WalletDAO {
    public WalletDAOImpl() {
        super(Wallet.class);
    }
}
