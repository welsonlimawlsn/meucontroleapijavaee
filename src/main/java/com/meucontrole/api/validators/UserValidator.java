package com.meucontrole.api.validators;

import com.meucontrole.api.entities.User;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.util.Message;

public class UserValidator {

    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static void validate(User user) throws BadRequestException {
        if (user == null) {
            throw new BadRequestException(Message.USUARIO_NAO_DEVE_SER_NULO);
        }

        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new BadRequestException(Message.NOME_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO);
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new BadRequestException(Message.SOBRENOME_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO);
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new BadRequestException(Message.EMAIL_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO);
        }

        if (!user.getEmail().matches(EMAIL_REGEX)) {
            throw new BadRequestException(Message.EMAIL_DO_USUARIO_INVALIDO);
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException(Message.SENHA_DO_USUARIO_NAO_DEVE_ESTAR_EM_BRANCO);
        }

        if (user.getPassword().length() < 8) {
            throw new BadRequestException(Message.SENHA_DO_USUARIO_DEVE_TER_NO_MINIMO_8_CARACTERES);
        }
    }
}
