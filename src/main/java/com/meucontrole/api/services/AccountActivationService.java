package com.meucontrole.api.services;

import com.meucontrole.api.dao.AccountActivationDAO;
import com.meucontrole.api.entities.AccountActivation;
import com.meucontrole.api.entities.ApplicationUser;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.util.Constants;
import com.meucontrole.api.util.Message;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;

@Stateless
public class AccountActivationService {

    @Inject
    private AccountActivationDAO dao;

    @EJB
    private MailService mailService;

    @EJB
    private ApplicationUserService applicationUserService;

    public void sendLinkForActivationOfAccount(ApplicationUser applicationUser) throws MessagingException {
        AccountActivation accountActivation = new AccountActivation(applicationUser);
        dao.insert(accountActivation);
        mailService.send(
                applicationUser.getEmail(),
                "Ativação da Conta",
                "<a href=\"" + Constants.FRONT_END_URL + "/account-activation/" + accountActivation.getId() + "\">Clique Aqui</a>"
        );
    }

    public void activateAccount(String accountActivationId) throws MessagingException, BadRequestException, NotFoundException {
        AccountActivation accountActivation = dao.findById(accountActivationId).orElseThrow(() -> new NotFoundException(Message.LINK_INVALIDO));
        accountActivation.use();
        dao.update(accountActivation);
        ApplicationUser applicationUser = accountActivation.getApplicationUser();
        if (!accountActivation.isValid()) {
            sendLinkForActivationOfAccount(applicationUser);
            throw new BadRequestException(Message.LINK_DE_ATIVACAO_EXPIRADO);
        }
        applicationUser.enable();
        applicationUserService.update(applicationUser);
    }
}
