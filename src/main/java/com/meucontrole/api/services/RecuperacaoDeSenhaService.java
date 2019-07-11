package com.meucontrole.api.services;

import com.meucontrole.api.dao.RecuperacaoDeSenhaDAO;
import com.meucontrole.api.entidades.RecuperacaoDeSenha;
import com.meucontrole.api.entidades.UsuarioDaAplicacao;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.util.Constantes;
import com.meucontrole.api.util.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@Stateless
public class RecuperacaoDeSenhaService {

    @Inject
    private RecuperacaoDeSenhaDAO dao;

    @EJB
    private UsuarioDaAplicacaoService usuarioDaAplicacaoService;

    @EJB
    private EmailService emailService;

    public void novoPedidoDeRecuperacaoDeSenha(String email) throws MessagingException {
        try {
            UsuarioDaAplicacao usuarioDaAplicacao = usuarioDaAplicacaoService.procurarPeloEmail(email);
            RecuperacaoDeSenha recuperacaoDeSenha = new RecuperacaoDeSenha();
            recuperacaoDeSenha.setUsuarioDaAplicacao(usuarioDaAplicacao);
            dao.inserir(recuperacaoDeSenha);
            emailService.enviar(usuarioDaAplicacao.getEmail(), "Recuperação de Senha", "<a href=\"" +
                    Constantes.FRONT_END_URL + "/recuperacao-senha/" + recuperacaoDeSenha.getId() + "\">Recuperar Senha</a>");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void mudarSenha(String idRecuperacaoDeSenha, String novaSenha) throws NotFoundException, BadRequestException, NoSuchAlgorithmException {
        RecuperacaoDeSenha recuperacaoDeSenha = dao.procurarPorIdEExpiracaoValida(idRecuperacaoDeSenha)
                .orElseThrow(() -> new NotFoundException(Mensagem.LINK_DE_RECUPERACAO_EXPIRADO));
        recuperacaoDeSenha.usar();
        dao.alterar(recuperacaoDeSenha);
        usuarioDaAplicacaoService.mudarSenha(recuperacaoDeSenha.getUsuarioDaAplicacao(), novaSenha);
    }
}
