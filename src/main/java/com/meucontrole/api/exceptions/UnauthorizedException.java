package com.meucontrole.api.exceptions;

import com.meucontrole.api.util.Mensagem;

import javax.ws.rs.core.Response;

public class UnauthorizedException extends ApplicationException {

    public UnauthorizedException(Mensagem mensagem) {
        super(mensagem, Response.Status.UNAUTHORIZED);
    }

    public UnauthorizedException(Mensagem mensagem, Throwable throwable) {
        super(mensagem, Response.Status.UNAUTHORIZED, throwable);
    }

}
