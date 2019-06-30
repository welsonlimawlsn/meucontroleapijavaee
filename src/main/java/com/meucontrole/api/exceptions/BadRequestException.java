package com.meucontrole.api.exceptions;

import com.meucontrole.api.util.Message;

import javax.ws.rs.core.Response;

public class BadRequestException extends ApplicationException {
    public BadRequestException(Message message) {
        super(message, Response.Status.BAD_REQUEST);
    }

    public BadRequestException(Message message, Throwable throwable) {
        super(message, Response.Status.BAD_REQUEST, throwable);
    }
}
