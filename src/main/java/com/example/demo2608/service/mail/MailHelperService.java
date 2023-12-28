package com.example.demo2608.service.mail;

import com.example.demo2608.config.MailConfig;
import com.example.demo2608.service.IMailSender;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Service
public class MailHelperService implements IMailSender {
    @SneakyThrows
    @Override
    public void sendMessageWithAttachment(String emailTo, String subject, String text, String pathToAttachment) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", MailConfig.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", MailConfig.SSL_PORT);
        MimeMessage mimeMessage = new MimeMessage(Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
            }
        }));

        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
        mimeMessage.setSubject(subject);
//        mimeMessage.setText(text);
        mimeMessage.setContent(text, "text/html; charset=utf-8");


//        create file attach
        // Create MimeBodyPart for the HTML content
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(text, "text/html");

        // Create MimeBodyPart for the attachment
        BodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.setFileName("booking_confirmation.html");
        attachmentPart.setContent(pathToAttachment, "text/html");

        // Create Multipart object to add both content and attachment
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentPart);

        Transport.send(mimeMessage);
      }



}
