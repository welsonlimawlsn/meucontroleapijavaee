package com.meucontrole.api.exceptions;

import com.meucontrole.api.util.Mensagem;

import javax.ws.rs.core.Response;

public class ApplicationException extends Exception {

    private Response.Status status;

    public ApplicationException(Mensagem mensagem, Response.Status status) {
        super(mensagem.toString());
        this.status = status;
    }

    public ApplicationException(Mensagem mensagem, Response.Status status, Throwable throwable) {
        super(mensagem.toString(), throwable);
        this.status = status;
    }

    public Response.Status getStatus() {
        return status;
    }

}
