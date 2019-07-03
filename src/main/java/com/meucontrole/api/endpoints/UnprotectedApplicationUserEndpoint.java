package com.meucontrole.api.endpoints;

import com.meucontrole.api.dto.user.LoginDTO;
import com.meucontrole.api.dto.user.NewUserDTO;
import com.meucontrole.api.entities.ApplicationUser;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.UnauthorizedException;
import com.meucontrole.api.services.ApplicationUserService;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/")
public class UnprotectedApplicationUserEndpoint {
    @EJB
    private ApplicationUserService applicationUserService;

    @POST
    @Path("login")
    public Response login(LoginDTO loginDTO) throws MessagingException, UnauthorizedException {
        String token = applicationUserService.login(loginDTO.getEmail(), loginDTO.getPassword());
        return Response.ok().header("Authorization", token).build();
    }

    @POST
    @Path("new-user")
    public Response newUser(NewUserDTO newUserDTO) throws MessagingException, BadRequestException {
        ApplicationUser applicationUser = applicationUserService.newUser(newUserDTO.convertToObject());
        return Response.created(UriBuilder.fromPath("/user/{id}").build(applicationUser.getId())).build();
    }
}
