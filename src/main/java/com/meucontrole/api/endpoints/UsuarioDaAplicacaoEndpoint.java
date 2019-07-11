package com.meucontrole.api.endpoints;

import com.meucontrole.api.dto.usuario.UsuarioDTO;
import com.meucontrole.api.entidades.Usuario;
import com.meucontrole.api.services.UsuarioDaAplicacaoService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioDaAplicacaoEndpoint {

    @EJB
    private UsuarioDaAplicacaoService usuarioDaAplicacaoService;

    @GET
    public Response getUser() {
        return Response.ok(UsuarioDTO.getDTOObject((Usuario) usuarioDaAplicacaoService.getAutorizado())).build();
    }

}
