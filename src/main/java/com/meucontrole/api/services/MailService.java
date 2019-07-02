package com.meucontrole.api.services;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Session;

@Stateless
public class MailService {

    @Resource(lookup = "java:/jboss/mail/MeuControleMail")
    private Session session;
}
