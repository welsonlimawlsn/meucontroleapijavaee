package com.meucontrole.api.services;

import com.meucontrole.api.dao.ApplicationUserDAO;
import com.meucontrole.api.entities.ApplicationUser;
import com.meucontrole.api.entities.User;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.exceptions.UnauthorizedException;
import com.meucontrole.api.session.ApplicationUserSession;
import com.meucontrole.api.util.ApplicationDate;
import com.meucontrole.api.util.Encryption;
import com.meucontrole.api.util.Message;
import com.meucontrole.api.validators.ApplicationUserValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@Stateless
public class ApplicationUserService {

    @Inject
    private ApplicationUserDAO dao;

    @EJB
    private AccountActivationService accountActivationService;

    @EJB
    private TokenService tokenService;

    @EJB
    private WalletService walletService;

    @EJB
    private AttemptSignInService attemptSignInService;

    @EJB
    private AccountBlockService accountBlockService;

    @Inject
    private ApplicationUserSession applicationUserSession;

    public ApplicationUser newUser(ApplicationUser applicationUser) throws MessagingException, BadRequestException, NoSuchAlgorithmException {
        ApplicationUserValidator.validate(applicationUser);
        throwIfEmailAlreadyExists(applicationUser);
        applicationUser.setEnabled(false);
        applicationUser.setPassword(Encryption.encrypt(applicationUser.getEmail() + "_" + applicationUser.getPassword()));
        dao.insert(applicationUser);
        accountActivationService.sendLinkForActivationOfAccount(applicationUser);
        walletService.newWallet((User) applicationUser);
        return applicationUser;
    }

    private void throwIfEmailAlreadyExists(ApplicationUser applicationUser) throws BadRequestException {
        if (dao.findByEmail(applicationUser.getEmail()).isPresent()) {
            throw new BadRequestException(Message.EMAIL_DUPLICADO);
        }
    }

    public ApplicationUser update(ApplicationUser applicationUser) throws NotFoundException {
        if (applicationUser == null || applicationUser.getId() == null || applicationUser.getId().isEmpty()) {
            throw new NotFoundException(Message.USUARIO_NAO_EXISTE);
        }
        dao.findById(applicationUser.getId()).orElseThrow(() -> new NotFoundException(Message.USUARIO_NAO_EXISTE));
        dao.update(applicationUser);
        return applicationUser;
    }

    public String login(String email, String password) throws MessagingException, UnauthorizedException, NoSuchAlgorithmException {
        if (accountBlockService.isBlocked(email)) {
            throw new UnauthorizedException(Message.CONTA_BLOQUEADA);
        }
        attemptSignInService.registerAttempt(email);
        attemptSignInService.verifyNumberOfAttemptsAndBlockIfNecessary(email);
        ApplicationUser applicationUser = dao.findByEmailAndPassword(email, Encryption.encrypt(email + "_" + password))
                .orElseThrow(() -> new UnauthorizedException(Message.EMAIL_OU_SENHA_INVALIDOS));
        boolean isNotPossibleAccessAccountWithoutActivating = applicationUser.getAccountCreationDate()
                .isBefore(ApplicationDate.localDateNow().minusDays(7)) && !applicationUser.getEnabled();
        if (isNotPossibleAccessAccountWithoutActivating) {
            accountActivationService.sendLinkForActivationOfAccount(applicationUser);
            throw new UnauthorizedException(Message.ATIVACAO_DA_CONTA_NECESSARIA);
        }
        return tokenService.generateToken(applicationUser);
    }

    public ApplicationUser findByEmail(String email) throws NotFoundException {
        return dao.findByEmail(email).orElseThrow(() -> new NotFoundException(Message.USUARIO_NAO_EXISTE));
    }

    public ApplicationUser getAuthorized() {
        return applicationUserSession.getAuthorized();
    }

    public void changePassword(ApplicationUser applicationUser, String newPassword) throws NoSuchAlgorithmException, NotFoundException {
        applicationUser.setPassword(Encryption.encrypt(applicationUser.getEmail() + "_" + newPassword));
        update(applicationUser);
    }

}
