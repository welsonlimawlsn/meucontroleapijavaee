package com.meucontrole.api.session;

import com.meucontrole.api.entidades.UsuarioDaAplicacao;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Sessao {

    private UsuarioDaAplicacao usuarioDaAplicacao;

    public UsuarioDaAplicacao getAutorizado() {
        return usuarioDaAplicacao;
    }

    public void autorizar(UsuarioDaAplicacao usuarioDaAplicacao) {
        this.usuarioDaAplicacao = usuarioDaAplicacao;
    }

}
