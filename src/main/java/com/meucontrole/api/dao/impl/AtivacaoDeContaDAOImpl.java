package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.AtivacaoDeContaDAO;
import com.meucontrole.api.entidades.AtivacaoDeConta;

public class AtivacaoDeContaDAOImpl extends GenericoDAOImpl<AtivacaoDeConta> implements AtivacaoDeContaDAO {

    public AtivacaoDeContaDAOImpl() {
        super(AtivacaoDeConta.class);
    }

}
