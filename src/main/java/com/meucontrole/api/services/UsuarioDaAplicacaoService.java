package com.meucontrole.api.services;

import com.meucontrole.api.dao.UsuarioDaAplicacaoDAO;
import com.meucontrole.api.entidades.Usuario;
import com.meucontrole.api.entidades.UsuarioDaAplicacao;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.exceptions.UnauthorizedException;
import com.meucontrole.api.session.Sessao;
import com.meucontrole.api.util.DataAplicacao;
import com.meucontrole.api.util.Criptografia;
import com.meucontrole.api.util.Mensagem;
import com.meucontrole.api.validators.ApplicationUserValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@Stateless
public class UsuarioDaAplicacaoService {

    @Inject
    private UsuarioDaAplicacaoDAO dao;

    @EJB
    private AtivacaoDeContaService ativacaoDeContaService;

    @EJB
    private TokenService tokenService;

    @EJB
    private CarteiraService carteiraService;

    @EJB
    private TentativaDeEntradaService tentativaDeEntradaService;

    @EJB
    private BloqueioDeContaService bloqueioDeContaService;

    @Inject
    private Sessao sessao;

    public UsuarioDaAplicacao novoUsuario(UsuarioDaAplicacao usuarioDaAplicacao) throws MessagingException, BadRequestException, NoSuchAlgorithmException {
        ApplicationUserValidator.validate(usuarioDaAplicacao);
        lancarExcecaoCasoUsuarioJaExista(usuarioDaAplicacao);
        usuarioDaAplicacao.setAtiva(false);
        usuarioDaAplicacao.setSenha(Criptografia.criptografar(usuarioDaAplicacao.getEmail() + "_" + usuarioDaAplicacao.getSenha()));
        dao.inserir(usuarioDaAplicacao);
        ativacaoDeContaService.enviarLinkParaAtivacaoDaConta(usuarioDaAplicacao);
        carteiraService.novaCarteira((Usuario) usuarioDaAplicacao);
        return usuarioDaAplicacao;
    }

    private void lancarExcecaoCasoUsuarioJaExista(UsuarioDaAplicacao usuarioDaAplicacao) throws BadRequestException {
        if (dao.procurarPorEmail(usuarioDaAplicacao.getEmail()).isPresent()) {
            throw new BadRequestException(Mensagem.EMAIL_DUPLICADO);
        }
    }

    public UsuarioDaAplicacao alterar(UsuarioDaAplicacao usuarioDaAplicacao) throws NotFoundException {
        boolean userIsNotValid = usuarioDaAplicacao == null || usuarioDaAplicacao.getId() == null
                || usuarioDaAplicacao.getId().isEmpty() || dao.procurarPorId(usuarioDaAplicacao.getId()).isEmpty();
        if (userIsNotValid) {
            throw new NotFoundException(Mensagem.USUARIO_NAO_EXISTE);
        }
        dao.alterar(usuarioDaAplicacao);
        return usuarioDaAplicacao;
    }

    public String login(String email, String senha) throws MessagingException, UnauthorizedException, NoSuchAlgorithmException {
        if (bloqueioDeContaService.estaBloquada(email)) {
            throw new UnauthorizedException(Mensagem.CONTA_BLOQUEADA);
        }
        tentativaDeEntradaService.registrarTentativa(email);
        tentativaDeEntradaService.verificarNumeroDeTentativasEBloquearCasoNecessario(email);
        UsuarioDaAplicacao usuarioDaAplicacao = dao.procurarPorEmailESenha(email, Criptografia.criptografar(email + "_" + senha))
                .orElseThrow(() -> new UnauthorizedException(Mensagem.EMAIL_OU_SENHA_INVALIDOS));
        boolean naoEhPossivelAcessarContaSemAtivar = usuarioDaAplicacao.getDataDeCriacao()
                .isBefore(DataAplicacao.localDateNow().minusDays(7)) && !usuarioDaAplicacao.getAtiva();
        if (naoEhPossivelAcessarContaSemAtivar) {
            ativacaoDeContaService.enviarLinkParaAtivacaoDaConta(usuarioDaAplicacao);
            throw new UnauthorizedException(Mensagem.ATIVACAO_DA_CONTA_NECESSARIA);
        }
        return tokenService.gerar(usuarioDaAplicacao);
    }

    public UsuarioDaAplicacao procurarPeloEmail(String email) throws NotFoundException {
        return dao.procurarPorEmail(email).orElseThrow(() -> new NotFoundException(Mensagem.USUARIO_NAO_EXISTE));
    }

    public UsuarioDaAplicacao getAutorizado() {
        return sessao.getAutorizado();
    }

    public void mudarSenha(UsuarioDaAplicacao usuarioDaAplicacao, String novaSenha) throws NoSuchAlgorithmException, NotFoundException {
        usuarioDaAplicacao.setSenha(Criptografia.criptografar(usuarioDaAplicacao.getEmail() + "_" + novaSenha));
        alterar(usuarioDaAplicacao);
    }

}
