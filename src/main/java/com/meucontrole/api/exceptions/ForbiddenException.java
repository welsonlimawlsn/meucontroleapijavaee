package com.meucontrole.api.exceptions;

import com.meucontrole.api.util.Mensagem;

import javax.ws.rs.core.Response;

public class ForbiddenException extends ApplicationException {

    public ForbiddenException(Mensagem mensagem) {
        super(mensagem, Response.Status.FORBIDDEN);
    }

}
