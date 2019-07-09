package com.meucontrole.api.endpoints;

import com.meucontrole.api.dto.user.LoginDTO;
import com.meucontrole.api.dto.user.NewPasswordDTO;
import com.meucontrole.api.dto.user.NewUserDTO;
import com.meucontrole.api.dto.user.PasswordRecoveryDTO;
import com.meucontrole.api.entities.ApplicationUser;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.exceptions.UnauthorizedException;
import com.meucontrole.api.services.ApplicationUserService;
import com.meucontrole.api.services.PasswordRecoveryService;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.security.NoSuchAlgorithmException;

@Path("/")
public class UnprotectedApplicationUserEndpoint {

    @EJB
    private ApplicationUserService applicationUserService;

    @EJB
    private PasswordRecoveryService passwordRecoveryService;

    @POST
    @Path("login")
    public Response login(LoginDTO loginDTO) throws MessagingException, UnauthorizedException, NoSuchAlgorithmException {
        String token = applicationUserService.login(loginDTO.getEmail(), loginDTO.getPassword());
        return Response.ok().header(HttpHeaders.AUTHORIZATION, token).build();
    }

    @POST
    @Path("new-user")
    public Response newUser(NewUserDTO newUserDTO) throws MessagingException, BadRequestException, NoSuchAlgorithmException {
        ApplicationUser applicationUser = applicationUserService.newUser(newUserDTO.convertToObject());
        return Response.created(UriBuilder.fromPath("/user").build(applicationUser.getId())).build();
    }

    @POST
    @Path("password-recovery")
    public Response newPasswordRecovery(PasswordRecoveryDTO dto) throws MessagingException {
        passwordRecoveryService.newPasswordRecovery(dto.getEmail());
        return Response.ok().build();
    }

    @PUT
    @Path("password-recovery/{id}")
    public Response changePassword(@PathParam("id") String id, NewPasswordDTO dto) throws NoSuchAlgorithmException,
            BadRequestException, NotFoundException {
        passwordRecoveryService.changePassword(id, dto.getNewPassword());
        return Response.ok().build();
    }

}
