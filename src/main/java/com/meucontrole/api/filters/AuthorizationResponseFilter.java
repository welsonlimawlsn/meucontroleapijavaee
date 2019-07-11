package com.meucontrole.api.filters;

import com.meucontrole.api.services.TokenService;
import com.meucontrole.api.session.Sessao;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthorizationResponseFilter implements ContainerResponseFilter {

    @Inject
    private Sessao sessao;

    @EJB
    private TokenService tokenService;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        if ((path.startsWith("/usuario") || path.startsWith("/admin")) && sessao.getAutorizado() != null) {
            String token = tokenService.gerar(sessao.getAutorizado());
            responseContext.getHeaders().add(HttpHeaders.AUTHORIZATION, token);
        }
    }

}
