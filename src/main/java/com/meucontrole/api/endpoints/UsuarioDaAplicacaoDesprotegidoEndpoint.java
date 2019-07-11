package com.meucontrole.api.endpoints;

import com.meucontrole.api.dto.usuario.LoginDTO;
import com.meucontrole.api.dto.usuario.NovaSenhaDTO;
import com.meucontrole.api.dto.usuario.NovoUsuarioDTO;
import com.meucontrole.api.dto.usuario.RecuperacaoDeSenhaDTO;
import com.meucontrole.api.entidades.UsuarioDaAplicacao;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.exceptions.UnauthorizedException;
import com.meucontrole.api.services.UsuarioDaAplicacaoService;
import com.meucontrole.api.services.RecuperacaoDeSenhaService;

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
public class UsuarioDaAplicacaoDesprotegidoEndpoint {

    @EJB
    private UsuarioDaAplicacaoService usuarioDaAplicacaoService;

    @EJB
    private RecuperacaoDeSenhaService recuperacaoDeSenhaService;

    @POST
    @Path("login")
    public Response login(LoginDTO loginDTO) throws MessagingException, UnauthorizedException, NoSuchAlgorithmException {
        String token = usuarioDaAplicacaoService.login(loginDTO.getEmail(), loginDTO.getSenha());
        return Response.ok().header(HttpHeaders.AUTHORIZATION, token).build();
    }

    @POST
    @Path("novo-usuario")
    public Response novoUsuario(NovoUsuarioDTO novoUsuarioDTO) throws MessagingException, BadRequestException, NoSuchAlgorithmException {
        UsuarioDaAplicacao usuarioDaAplicacao = usuarioDaAplicacaoService.novoUsuario(novoUsuarioDTO.convertToObject());
        return Response.created(UriBuilder.fromPath("/usuario").build(usuarioDaAplicacao.getId())).build();
    }

    @POST
    @Path("recuperacao-senha")
    public Response novaRecuperacaoDeSenha(RecuperacaoDeSenhaDTO dto) throws MessagingException {
        recuperacaoDeSenhaService.novoPedidoDeRecuperacaoDeSenha(dto.getEmail());
        return Response.ok().build();
    }

    @PUT
    @Path("recuperacao-senha/{id}")
    public Response mudarSenha(@PathParam("id") String id, NovaSenhaDTO dto) throws NoSuchAlgorithmException,
            BadRequestException, NotFoundException {
        recuperacaoDeSenhaService.mudarSenha(id, dto.getNovaSenha());
        return Response.ok().build();
    }

}
