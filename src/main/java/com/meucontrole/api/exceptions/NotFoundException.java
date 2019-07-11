package com.meucontrole.api.exceptions;

import com.meucontrole.api.util.Mensagem;

import javax.ws.rs.core.Response;

public class NotFoundException extends ApplicationException {

    public NotFoundException(Mensagem mensagem) {
        super(mensagem, Response.Status.NOT_FOUND);
    }

}
