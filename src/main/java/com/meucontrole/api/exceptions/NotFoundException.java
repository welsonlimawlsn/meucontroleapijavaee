package com.meucontrole.api.exceptions;

import com.meucontrole.api.util.Message;

import javax.ws.rs.core.Response;

public class NotFoundException extends ApplicationException {

    public NotFoundException(Message message) {
        super(message, Response.Status.NOT_FOUND);
    }

}
