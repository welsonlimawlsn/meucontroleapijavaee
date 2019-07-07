package com.meucontrole.api.exceptions;

import com.meucontrole.api.util.Message;

import javax.ws.rs.core.Response;

public class ForbiddenException extends ApplicationException {

    public ForbiddenException(Message message) {
        super(message, Response.Status.FORBIDDEN);
    }

}
