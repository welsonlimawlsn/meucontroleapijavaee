package com.meucontrole.api.services;

import com.meucontrole.api.dao.BloqueioDeContaDAO;
import com.meucontrole.api.entidades.BloqueioDeConta;
import com.meucontrole.api.util.DataAplicacao;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BloqueioDeContaService {

    @Inject
    private BloqueioDeContaDAO dao;

    public void bloquear(String email) {
        BloqueioDeConta bloqueioDeConta = new BloqueioDeConta();
        bloqueioDeConta.setEmail(email);
        bloqueioDeConta.setExpiracao(DataAplicacao.localDateTimeNow().plusMinutes(30));
        dao.inserir(bloqueioDeConta);
    }

    public boolean estaBloquada(String email) {
        return dao.procurarPorEmailDesdeAData(email, DataAplicacao.localDateTimeNow()).isPresent();
    }

}
