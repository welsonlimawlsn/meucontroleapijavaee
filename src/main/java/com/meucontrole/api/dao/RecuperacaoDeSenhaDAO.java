package com.meucontrole.api.dao;

import com.meucontrole.api.entidades.RecuperacaoDeSenha;

import java.util.Optional;

public interface RecuperacaoDeSenhaDAO extends GenericoDAO<RecuperacaoDeSenha> {

    Optional<RecuperacaoDeSenha> procurarPorIdEExpiracaoValida(String id);
}
