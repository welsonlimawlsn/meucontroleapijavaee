package com.meucontrole.api.endpoints;

import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.services.AccountActivationService;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/account-activation")
public class AccountActivationEndpoint {

    @EJB
    private AccountActivationService accountActivationService;

    @Path("{id}")
    @GET
    public Response activateAccount(@PathParam("id") String id) throws MessagingException, BadRequestException, NotFoundException {
        accountActivationService.activateAccount(id);
        return Response.ok().build();
    }
}
