package com.residencia.notificacion.service;

import com.residencia.notificacion.model.db.Notificacion;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NotificacionServiceImp implements NotificacionService{
    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String sender;
    // Method 1
    // To send a simple email

    public String sendSimpleMail(Notificacion notificacionRequest)
    {
        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        // Try block to check for exceptions
        try {
/*

            // Creating a simple mail message
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            // Setting up necessary notificacionRequest
            mailMessage.setFrom(sender);
            mailMessage.setTo(InternetAddress.parse(notificacionRequest.getRecipient()));
            mailMessage.setText(notificacionRequest.getMsgBody());
            mailMessage.setSubject(notificacionRequest.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";


 */
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, false);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(InternetAddress.parse(notificacionRequest.getRecipient()));
            mimeMessageHelper.setText(notificacionRequest.getMsgBody());
            mimeMessageHelper.setSubject(
                    notificacionRequest.getSubject());

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
        // Catch block to handle the exceptions
        catch (Exception e) {
            log.info(e.getMessage());
            return "Error while Sending Mail";
        }
    }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(Notificacion notificacionRequest)
    {
        String [] base64String= notificacionRequest.getAttachment().split(",");
        byte[] data = Base64.decodeBase64(base64String[1]);
        String path= "C:/Users/erick/Downloads/documento.pdf";
        File fil= new File(path);
        try (OutputStream stream = new BufferedOutputStream(new FileOutputStream(fil))) {
            stream.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Creating a mime message

        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send

            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(InternetAddress.parse(notificacionRequest.getRecipient()));
            mimeMessageHelper.setText(notificacionRequest.getMsgBody());
            mimeMessageHelper.setSubject(
                    notificacionRequest.getSubject());

            // Adding the attachment
            FileSystemResource file = new FileSystemResource(new File(path));

            mimeMessageHelper.addAttachment(file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}
