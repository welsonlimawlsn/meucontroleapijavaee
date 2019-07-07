package com.meucontrole.api.endpoints;

import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.services.WalletService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("user/wallet")
public class WalletEndpoint {

    @EJB
    private WalletService walletService;

    @GET
    public Response getWallet() throws NotFoundException {
        return Response.ok(walletService.getWalletUserAuthorized()).build();
    }

}
