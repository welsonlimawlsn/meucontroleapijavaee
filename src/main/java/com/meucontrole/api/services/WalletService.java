package com.meucontrole.api.services;

import com.meucontrole.api.dao.WalletDAO;
import com.meucontrole.api.entities.User;
import com.meucontrole.api.entities.Wallet;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.session.ApplicationUserSession;
import com.meucontrole.api.util.Message;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class WalletService {

    @Inject
    private WalletDAO dao;

    @Inject
    private ApplicationUserSession applicationUserSession;

    public Wallet newWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return dao.insert(wallet);
    }

    public Wallet getWalletUserAuthorized() throws NotFoundException {
        return dao.findByUser((User) applicationUserSession.getAuthorized())
                .orElseThrow(() -> new NotFoundException(Message.CARTEIRA_NAO_ENCOTRADA));
    }

}
