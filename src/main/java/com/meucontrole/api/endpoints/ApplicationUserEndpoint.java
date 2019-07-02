package com.meucontrole.api.endpoints;

import com.meucontrole.api.dto.NewUserDTO;
import com.meucontrole.api.dto.UserDTO;
import com.meucontrole.api.entities.ApplicationUser;
import com.meucontrole.api.entities.User;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.services.ApplicationUserService;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApplicationUserEndpoint {

    @EJB
    private ApplicationUserService applicationUserService;

    @POST
    public Response newUser(NewUserDTO newUserDTO) throws BadRequestException, MessagingException {
        ApplicationUser applicationUser = applicationUserService.newUser(newUserDTO.convertToObject());
        return Response.created(UriBuilder.fromPath("/user/{id}").build(applicationUser.getId())).build();
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") String id) throws NotFoundException {
        return Response.ok(UserDTO.getDTOObject((User) applicationUserService.getUserById(id))).build();
    }
}
