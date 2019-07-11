package com.meucontrole.api.exceptions;

import com.meucontrole.api.util.Mensagem;

import javax.ws.rs.core.Response;

public class BadRequestException extends ApplicationException {

    public BadRequestException(Mensagem mensagem) {
        super(mensagem, Response.Status.BAD_REQUEST);
    }

    public BadRequestException(Mensagem mensagem, Throwable throwable) {
        super(mensagem, Response.Status.BAD_REQUEST, throwable);
    }

}
