/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public Map<String, String> SendEmail(String toEmail, String body)
            throws SendFailedException {
        Map<String, String> valuesMap = new HashMap<>();
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("cristhian.crypton@gmail.com");
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setText(body);
            simpleMailMessage.setSubject("ToLand App - User Credential Recovery");
            javaMailSender.send(simpleMailMessage);
            valuesMap.put("Status", "ok");
            valuesMap.put("Response", "Enviado");
            return valuesMap;
        } catch (Exception e) {
            valuesMap.put("Status", "Error");
            valuesMap.put("Response", e.getMessage());
            return valuesMap;
        }
    }

    public Map<String, String> SendEmailVersion2(String toEmail, String body) {
        Map<String, String> valuesMap = new HashMap<>();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tolan.configuration@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("ToLand App - User Credential Recovery");
            message.setText(body);
            Transport t = session.getTransport("smtp");
            t.connect("tolan.configuration@gmail.com", "qwertyuiop1234567890ASDFGHJKL");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            valuesMap.put("Status", "ok");
            valuesMap.put("Response", "Email Enviado");
            return valuesMap;
        } catch (MessagingException e) {
            valuesMap.put("Status", "Error");
            valuesMap.put("Response", e.getMessage());
            return valuesMap;
        }//qwertyuiop1234567890ASDFGHJKL
    }

}
