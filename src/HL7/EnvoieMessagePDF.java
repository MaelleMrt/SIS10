/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HL7;

import static java.lang.ProcessBuilder.Redirect.to;
import static java.util.Date.from;
import java.util.Properties;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.mail.Address;

/**
 * Envoie mail avec une pièce jointe
 * @author Maelle
 */
// envoie message avec une piece jointe
public class EnvoieMessagePDF {
    

/**
 * Envoie mail avec une pièce jointe
 * @param subject sujet du mail
 * @param text contenu du mail
 * @param destinataire adresse mail du destinataire
 * @param copyDest adresse mail du destinataire
 * @param idePatient identifiant du patient concerne
 * @author Maelle
 */
    public void sendMessage(String subject, String text, String destinataire, String copyDest,int idPatient) {
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
      // Créer un message
     // Define message
      message.setText(text);
      message.setSubject(subject);
      message.addRecipients(Message.RecipientType.TO, destinataire);
      message.addRecipients(Message.RecipientType.CC, copyDest);

    // Create the message part
    BodyPart messageBodyPart = new MimeBodyPart();

    // Fill the message
    messageBodyPart.setText(text);

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);

    // Part two is attachment
    messageBodyPart = new MimeBodyPart();
    DataSource source = new FileDataSource("src/PDF/LettreSortie123456789.pdf");
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName("src/PDF/LettreSortie"+idPatient+".pdf");
    multipart.addBodyPart(messageBodyPart);

    // Put parts in message
    message.setContent(multipart);
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
 
    } catch (MessagingException mex) {
      mex.printStackTrace();
      Exception ex = null;
      if ((ex = mex.getNextException()) != null) {
    ex.printStackTrace();
      }
  }
    }
}
