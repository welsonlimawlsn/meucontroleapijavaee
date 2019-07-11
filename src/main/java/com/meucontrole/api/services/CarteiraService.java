package com.meucontrole.api.services;

import com.meucontrole.api.dao.CarteiraDAO;
import com.meucontrole.api.entidades.Carteira;
import com.meucontrole.api.entidades.Usuario;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.session.Sessao;
import com.meucontrole.api.util.Mensagem;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CarteiraService {

    @Inject
    private CarteiraDAO dao;

    @Inject
    private Sessao sessao;

    public Carteira novaCarteira(Usuario usuario) {
        Carteira carteira = new Carteira();
        carteira.setUsuario(usuario);
        return dao.inserir(carteira);
    }

    public Carteira getCarteiraUsuarioAutorizado() throws NotFoundException {
        return dao.procurarPorUsuario((Usuario) sessao.getAutorizado())
                .orElseThrow(() -> new NotFoundException(Mensagem.CARTEIRA_NAO_ENCOTRADA));
    }

}
