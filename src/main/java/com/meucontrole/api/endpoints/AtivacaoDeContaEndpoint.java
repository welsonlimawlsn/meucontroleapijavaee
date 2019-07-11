package com.meucontrole.api.endpoints;

import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.services.AtivacaoDeContaService;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/ativacao-conta")
public class AtivacaoDeContaEndpoint {

    @EJB
    private AtivacaoDeContaService ativacaoDeContaService;

    @Path("{id}")
    @GET
    public Response ativarConta(@PathParam("id") String id) throws MessagingException, BadRequestException, NotFoundException {
        ativacaoDeContaService.ativarConta(id);
        return Response.ok().build();
    }

}
