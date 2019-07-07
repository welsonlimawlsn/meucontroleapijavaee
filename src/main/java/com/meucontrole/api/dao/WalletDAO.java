package com.meucontrole.api.dao;

import com.meucontrole.api.entities.User;
import com.meucontrole.api.entities.Wallet;

import java.util.Optional;

public interface WalletDAO extends GenericDAO<Wallet> {

    Optional<Wallet> findByUser(User user);

}
