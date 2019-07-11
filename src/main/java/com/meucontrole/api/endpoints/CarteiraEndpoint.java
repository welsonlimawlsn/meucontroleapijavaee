package com.meucontrole.api.endpoints;

import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.services.CarteiraService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("usuario/carteira")
public class CarteiraEndpoint {

    @EJB
    private CarteiraService carteiraService;

    @GET
    public Response getCarteira() throws NotFoundException {
        return Response.ok(carteiraService.getCarteiraUsuarioAutorizado()).build();
    }

}
