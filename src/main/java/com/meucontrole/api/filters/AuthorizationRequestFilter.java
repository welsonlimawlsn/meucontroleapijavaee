package com.meucontrole.api.filters;

import com.meucontrole.api.exceptions.ForbiddenException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.exceptions.UnauthorizedException;
import com.meucontrole.api.exceptions.errorhandler.ApplicationExceptionHandler;
import com.meucontrole.api.services.UsuarioDaAplicacaoService;
import com.meucontrole.api.services.TokenService;
import com.meucontrole.api.session.Sessao;
import com.meucontrole.api.util.Mensagem;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {

    @EJB
    private TokenService tokenService;

    @Inject
    private Sessao sessao;

    @EJB
    private UsuarioDaAplicacaoService usuarioDaAplicacaoService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getUriInfo().getPath().startsWith("/usuario")) {
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
            throw new ForbiddenException(Mensagem.VOCE_NAO_TEM_PERMISSAO_PARA_ACESSAR_ISTO);
        }
        try {
            sessao.autorizar(usuarioDaAplicacaoService.procurarPeloEmail(subject));
        } catch (NotFoundException e) {
            throw new ForbiddenException(Mensagem.VOCE_NAO_TEM_PERMISSAO_PARA_ACESSAR_ISTO);
        }
    }

    private String getSubject(ContainerRequestContext requestContext) throws ForbiddenException, UnauthorizedException {
        String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new ForbiddenException(Mensagem.VOCE_NAO_TEM_PERMISSAO_PARA_ACESSAR_ISTO);
        }
        return tokenService.getSubject(authorization);
    }

}
