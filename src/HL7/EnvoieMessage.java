/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HL7;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Maelle
 */
public class EnvoieMessage {
    
    public void sendMessage(String subject, String text, String destinataire, String copyDest) {
    // 1 -> Création de la session
   
    Properties properties = new Properties();
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.host", "smtp.gmail.com");
    properties.setProperty("mail.smtp.user", "maelle.mg@gmail.com");
    properties.setProperty("mail.from", "imap.gmail.com");
    properties.setProperty("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.port",587);

    Session session = Session.getInstance(properties);
    
    MimeMessage message = new MimeMessage(session);
    try {
        message.setText(text);
        message.setSubject(subject);
        message.addRecipients(Message.RecipientType.TO, destinataire);
        message.addRecipients(Message.RecipientType.CC, copyDest);
    } catch (MessagingException e) {
        e.printStackTrace();
    }
     // 3 -> Envoi du message
    Transport transport=null;
    try {
        transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com","hopital.princeton@gmail.com", "HopitalPrinceton2021!");

        transport.sendMessage(message, new Address[] { new InternetAddress(destinataire),
                                                        new InternetAddress(copyDest) });
    } catch (MessagingException e) {
        e.printStackTrace();
    } finally {
        try {
            if (transport != null) {
                transport.close();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
        System.out.println("message envoyé");
    } 
}
