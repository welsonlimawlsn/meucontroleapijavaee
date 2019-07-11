package com.meucontrole.api.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Erro {

    private String mensagemDesenvolvimento;
    private String mensagem;
    private int status;

    public Erro(String mensagemDesenvolvimento, String mensagem, int status) {
        this.mensagemDesenvolvimento = mensagemDesenvolvimento;
        this.mensagem = mensagem;
        this.status = status;
    }

}
