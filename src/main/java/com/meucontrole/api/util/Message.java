package com.meucontrole.api.util;

public enum Message {

    CONTA_JA_ATIVADA("Essa conta já está ativado."),
    TRANSACAO_JA_CONSOLIDADA("Essa transação já foi consolidada."),
    DESPESA_PRECISA_SER_NEGATIVA("Uma despesa precisa ter um valor negativo."),
    RECEITA_PRECISA_SER_POSITIVO("Uma receita precisa ter um valor povitivo."),
    QUANTIDADE_MINIMA_TRANSACAO_COM_REPETICAO("Uma transação com repetição deve ter no minimo 2 repetições."),
    USUARIO_NAO_DEVE_SER_NULO("Usuário não deve ser nulo."),
    NOME_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO("Nome do usuário não deve estar em branco."),
    SOBRENOME_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO("Sobrenome do usuário não deve estar em branco."),
    EMAIL_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO("E-mail do usuário não deve estar em branco."),
    SENHA_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO("Senha do usuário não deve estar em branco."),
    SENHA_DO_USUARIO_DEVE_TER_NO_MINIMO_8_CARACTERES("Senha do usuário deve ter no minimo 8 caracteres."),
    EMAIL_DO_USUARIO_INVALIDO("E-mail do usuário é invalido."),
    EMAIL_DUPLICADO("E-mail duplicado."),
    LINK_INVALIDO("Link invalido."),
    USUARIO_NAO_EXISTE("Usuário não existe."),
    LINK_DE_ATIVACAO_EXPIRADO("Link de ativação expirado, em breve chegará outro em seu e-mail."),
    LINK_DE_ATIVACAO_JA_USADO("Link de ativação já usado."),
    EMAIL_OU_SENHA_INVALIDOS("E-mail e/ou senha invalidos."),
    TOKEN_EXPIRADO("Seu login expirou, faça o login novamente!"),
    ATIVACAO_DA_CONTA_NECESSARIA("Ativação da conta é necessaria, reeviamos o email de ativação."),
    VOCE_NAO_TEM_PERMISSAO_PARA_ACESSAR_ISTO("Você não tem permissão para acessar isto!"),
    CARTEIRA_NAO_ENCOTRADA("Carteira não encontrada."),
    CONTA_BLOQUEADA("Conta bloqueada, aguarde 30 minutos e tente novamente."),
    LINK_DE_RECUPERACAO_USADO("Link de recuperção já foi usado"),
    LINK_DE_RECUPERACAO_EXPIRADO("Link de recuperação expirado, peça novamente.");

    private String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
