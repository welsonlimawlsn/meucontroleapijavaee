package com.meucontrole.api.services;

import com.meucontrole.api.dao.AtivacaoDeContaDAO;
import com.meucontrole.api.entidades.AtivacaoDeConta;
import com.meucontrole.api.entidades.UsuarioDaAplicacao;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.util.Constantes;
import com.meucontrole.api.util.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;

@Stateless
public class AtivacaoDeContaService {

    @Inject
    private AtivacaoDeContaDAO dao;

    @EJB
    private EmailService emailService;

    @EJB
    private UsuarioDaAplicacaoService usuarioDaAplicacaoService;

    public void enviarLinkParaAtivacaoDaConta(UsuarioDaAplicacao usuarioDaAplicacao) throws MessagingException {
        AtivacaoDeConta ativacaoDeConta = new AtivacaoDeConta(usuarioDaAplicacao);
        dao.inserir(ativacaoDeConta);
        emailService.enviar(
                usuarioDaAplicacao.getEmail(),
                "Ativação da Conta",
                "<a href=\"" + Constantes.FRONT_END_URL + "/ativacao-conta/" + ativacaoDeConta.getId() + "\">Clique Aqui</a>"
        );
    }

    public void ativarConta(String idAtivacaoDeConta) throws MessagingException, BadRequestException, NotFoundException {
        AtivacaoDeConta ativacaoDeConta = dao.procurarPorId(idAtivacaoDeConta).orElseThrow(() -> new NotFoundException(Mensagem.LINK_INVALIDO));
        ativacaoDeConta.usar();
        dao.alterar(ativacaoDeConta);
        UsuarioDaAplicacao usuarioDaAplicacao = ativacaoDeConta.getUsuarioDaAplicacao();
        if (!ativacaoDeConta.ehValida()) {
            enviarLinkParaAtivacaoDaConta(usuarioDaAplicacao);
            throw new BadRequestException(Mensagem.LINK_DE_ATIVACAO_EXPIRADO);
        }
        usuarioDaAplicacao.ativar();
        usuarioDaAplicacaoService.alterar(usuarioDaAplicacao);
    }

}
