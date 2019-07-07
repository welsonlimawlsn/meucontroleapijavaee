package com.meucontrole.api.exceptions;

import com.meucontrole.api.util.Message;

import javax.ws.rs.core.Response;

public class UnauthorizedException extends ApplicationException {

    public UnauthorizedException(Message message) {
        super(message, Response.Status.UNAUTHORIZED);
    }

    public UnauthorizedException(Message message, Throwable throwable) {
        super(message, Response.Status.UNAUTHORIZED, throwable);
    }

}
