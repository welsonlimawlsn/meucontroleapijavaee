package com.meucontrole.api.endpoints;

import com.meucontrole.api.dto.user.UserDTO;
import com.meucontrole.api.entities.User;
import com.meucontrole.api.services.ApplicationUserService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApplicationUserEndpoint {

    @EJB
    private ApplicationUserService applicationUserService;

    @GET
    public Response getUser() {
        return Response.ok(UserDTO.getDTOObject((User) applicationUserService.getAuthorized())).build();
    }

}
