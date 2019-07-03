package com.meucontrole.api.services;

import com.meucontrole.api.dao.WalletDAO;
import com.meucontrole.api.entities.User;
import com.meucontrole.api.entities.Wallet;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class WalletService {

    @Inject
    private WalletDAO dao;

    public Wallet newWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return dao.insert(wallet);
    }
}
