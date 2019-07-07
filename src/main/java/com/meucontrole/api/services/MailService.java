package com.meucontrole.api.services;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;

@Stateless
public class MailService {

    @Resource(lookup = "java:/jboss/mail/MeuControleMail")
    private Session session;

    @Asynchronous
    public void send(String to, String subject, String html) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setContent(html, MediaType.TEXT_HTML);
        message.setSubject(subject);
        Transport.send(message);
        System.out.println("Email enviado para " + to);
    }

}
