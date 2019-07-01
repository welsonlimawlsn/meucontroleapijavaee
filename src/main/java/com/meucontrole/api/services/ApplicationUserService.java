package com.meucontrole.api.services;

import com.meucontrole.api.dao.UserDAO;
import com.meucontrole.api.entities.User;
import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.exceptions.NotFoundException;
import com.meucontrole.api.util.Message;
import com.meucontrole.api.validators.UserValidator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ApplicationUserService {

    @Inject
    private UserDAO dao;

    public User newUser(User user) throws BadRequestException {
        UserValidator.validate(user);
        user.setEnabled(false);
        dao.insert(user);
        return user;
    }

    public User getUserById(String id) throws NotFoundException {
        return dao.findById(id).orElseThrow(() -> new NotFoundException(Message.CONTA_JA_ATIVADA));
    }
}