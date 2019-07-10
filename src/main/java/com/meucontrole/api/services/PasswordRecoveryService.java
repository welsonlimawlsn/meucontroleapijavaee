package com.meucontrole.api.services;

import com.meucontrole.api.dao.PasswordRecoveryDAO;
import com.meucontrole.api.entities.ApplicationUser;
import com.meucontrole.api.entities.PasswordRecovery;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.util.Constants;
import com.meucontrole.api.util.Message;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@Stateless
public class PasswordRecoveryService {

    @Inject
    private PasswordRecoveryDAO dao;

    @EJB
    private ApplicationUserService applicationUserService;

    @EJB
    private MailService mailService;

    public void newPasswordRecovery(String email) throws MessagingException {
        try {
            ApplicationUser applicationUser = applicationUserService.findByEmail(email);
            PasswordRecovery passwordRecovery = new PasswordRecovery();
            passwordRecovery.setApplicationUser(applicationUser);
            dao.insert(passwordRecovery);
            mailService.send(applicationUser.getEmail(), "Recuperação de Senha", "<a href=\"" +
                    Constants.FRONT_END_URL + "/password-recovery/" + passwordRecovery.getId() + "\">Recuperar Senha</a>");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changePassword(String idPasswordRecovery, String newPassword) throws NotFoundException, BadRequestException, NoSuchAlgorithmException {
        PasswordRecovery passwordRecovery = dao.findByIdAndValidExpiration(idPasswordRecovery)
                .orElseThrow(() -> new NotFoundException(Message.LINK_DE_RECUPERACAO_EXPIRADO));
        passwordRecovery.use();
        dao.update(passwordRecovery);
        applicationUserService.changePassword(passwordRecovery.getApplicationUser(), newPassword);
    }
}
