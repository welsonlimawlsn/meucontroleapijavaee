package com.meucontrole.api.exceptions.errorhandler;

import com.meucontrole.api.exceptions.ApplicationException;
import com.meucontrole.api.util.Error;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> {

    @Override
    public Response toResponse(ApplicationException exception) {
        String cause = exception.getCause() != null ? exception.getCause().getMessage() : "Nenhuma menssagem definida.";
        Error error = new Error(cause, exception.getMessage(), exception.getStatus().getStatusCode());
        return Response
                .status(exception.getStatus())
                .entity(error)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

}
