package com.meucontrole.api.services;

import com.meucontrole.api.dao.TentativaDeEntradaDAO;
import com.meucontrole.api.entidades.TentativaEntrada;
import com.meucontrole.api.exceptions.UnauthorizedException;
import com.meucontrole.api.util.DataAplicacao;
import com.meucontrole.api.util.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TentativaDeEntradaService {

    @Inject
    private TentativaDeEntradaDAO dao;

    @EJB
    private BloqueioDeContaService bloqueioDeContaService;

    public void registrarTentativa(String email) {
        TentativaEntrada tentativaEntrada = new TentativaEntrada();
        tentativaEntrada.setEmail(email);
        tentativaEntrada.setData(DataAplicacao.localDateTimeNow());
        dao.inserir(tentativaEntrada);
    }

    public void verificarNumeroDeTentativasEBloquearCasoNecessario(String email) throws UnauthorizedException {
        long numberOfAttemptByEmailAndSinceDateTime = dao.getNumeroDeTentativasPorEmailDesdeAData(email,
                DataAplicacao.localDateTimeNow().minusMinutes(1));
        if (numberOfAttemptByEmailAndSinceDateTime > 3) {
            bloqueioDeContaService.bloquear(email);
            throw new UnauthorizedException(Mensagem.CONTA_BLOQUEADA);
        }
    }

}
