package com.meucontrole.api.filters;

import com.meucontrole.api.exceptions.ForbiddenException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.exceptions.UnauthorizedException;
import com.meucontrole.api.exceptions.errorhandler.ApplicationExceptionHandler;
import com.meucontrole.api.services.ApplicationUserService;
import com.meucontrole.api.services.TokenService;
import com.meucontrole.api.session.ApplicationUserSession;
import com.meucontrole.api.util.Message;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter {

    @EJB
    private TokenService tokenService;

    @Inject
    private ApplicationUserSession applicationUserSession;

    @EJB
    private ApplicationUserService applicationUserService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getUriInfo().getPath().startsWith("/user")) {
            try {
                String subject = getSubject(requestContext);
                authorizeSubject(subject);
            } catch (ForbiddenException | UnauthorizedException e) {
                requestContext.abortWith(new ApplicationExceptionHandler().toResponse(e));
            }
        }
    }

    private void authorizeSubject(String subject) throws ForbiddenException {
        if (subject == null) {
            throw new ForbiddenException(Message.VOCE_NAO_TEM_PERMISSAO_PARA_ACESSAR_ISTO);
        }
        try {
            applicationUserSession.authorize(applicationUserService.findByEmail(subject));
        } catch (NotFoundException e) {
            throw new ForbiddenException(Message.VOCE_NAO_TEM_PERMISSAO_PARA_ACESSAR_ISTO);
        }
    }

    private String getSubject(ContainerRequestContext requestContext) throws ForbiddenException, UnauthorizedException {
        String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new ForbiddenException(Message.VOCE_NAO_TEM_PERMISSAO_PARA_ACESSAR_ISTO);
        }
        return tokenService.getSubject(authorization);
    }
}
