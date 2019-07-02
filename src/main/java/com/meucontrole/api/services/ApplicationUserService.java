package com.meucontrole.api.services;

import com.meucontrole.api.dao.ApplicationUserDAO;
import com.meucontrole.api.entities.ApplicationUser;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.util.Message;
import com.meucontrole.api.validators.ApplicationUserValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Stateless
public class ApplicationUserService {

    @Inject
    private ApplicationUserDAO dao;

    @Context
    private SecurityContext securityContext;

    @EJB
    private AccountActivationService accountActivationService;

    public ApplicationUser newUser(ApplicationUser applicationUser) throws BadRequestException, MessagingException {
        ApplicationUserValidator.validate(applicationUser);
        throwIfEmailAlreadyExists(applicationUser);
        applicationUser.setEnabled(false);
        dao.insert(applicationUser);
        accountActivationService.sendLinkForActivationOfAccount(applicationUser);
        return applicationUser;
    }

    private void throwIfEmailAlreadyExists(ApplicationUser applicationUser) throws BadRequestException {
        if (dao.findByEmail(applicationUser.getEmail()).isPresent()) {
            throw new BadRequestException(Message.EMAIL_DUPLICADO);
        }
    }

    public ApplicationUser getUserById(String id) throws NotFoundException {
        return dao.findById(id).orElseThrow(() -> new NotFoundException(Message.CONTA_JA_ATIVADA));
    }

    public ApplicationUser update(ApplicationUser applicationUser) throws NotFoundException {
        if (applicationUser == null || applicationUser.getId() == null || applicationUser.getId().isEmpty()) {
            throw new NotFoundException(Message.USUARIO_NAO_EXISTE);
        }
        dao.findById(applicationUser.getId()).orElseThrow(() -> new NotFoundException(Message.USUARIO_NAO_EXISTE));
        dao.update(applicationUser);
        return applicationUser;
    }
}
