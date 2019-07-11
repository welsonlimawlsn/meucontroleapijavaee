package com.meucontrole.api.validators;

import com.meucontrole.api.entidades.UsuarioDaAplicacao;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.util.Mensagem;

public class ApplicationUserValidator {

    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static void validate(UsuarioDaAplicacao user) throws BadRequestException {
        if (user == null) {
            throw new BadRequestException(Mensagem.USUARIO_NAO_DEVE_SER_NULO);
        }

        if (user.getNome() == null || user.getNome().isEmpty()) {
            throw new BadRequestException(Mensagem.NOME_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO);
        }

        if (user.getSobrenome() == null || user.getSobrenome().isEmpty()) {
            throw new BadRequestException(Mensagem.SOBRENOME_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO);
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new BadRequestException(Mensagem.EMAIL_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO);
        }

        if (!user.getEmail().matches(EMAIL_REGEX)) {
            throw new BadRequestException(Mensagem.EMAIL_DO_USUARIO_INVALIDO);
        }

        if (user.getSenha() == null || user.getSenha().isEmpty()) {
            throw new BadRequestException(Mensagem.SENHA_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO);
        }

        if (user.getSenha().length() < 8) {
            throw new BadRequestException(Mensagem.SENHA_DO_USUARIO_DEVE_TER_NO_MINIMO_8_CARACTERES);
        }
    }

}
