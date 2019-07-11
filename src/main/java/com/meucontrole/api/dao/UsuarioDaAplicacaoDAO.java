package com.meucontrole.api.dao;

import com.meucontrole.api.entidades.UsuarioDaAplicacao;

import java.util.Optional;

public interface UsuarioDaAplicacaoDAO extends GenericoDAO<UsuarioDaAplicacao> {

    Optional<UsuarioDaAplicacao> procurarPorEmailESenha(String email, String senha);

    Optional<UsuarioDaAplicacao> procurarPorEmail(String email);

}
