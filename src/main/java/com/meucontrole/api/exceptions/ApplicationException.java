package com.meucontrole.api.exceptions;

import com.meucontrole.api.util.Message;

import javax.ws.rs.core.Response;

public class ApplicationException extends Exception {

    private Response.Status status;

    public ApplicationException(Message message, Response.Status status) {
        super(message.toString());
        this.status = status;
    }

    public ApplicationException(Message message, Response.Status status, Throwable throwable) {
        super(message.toString(), throwable);
        this.status = status;
    }

    public Response.Status getStatus() {
        return status;
    }

}
