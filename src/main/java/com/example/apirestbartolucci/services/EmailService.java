/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import java.util.HashMap;
import java.util.Map;
import javax.mail.SendFailedException;
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
            simpleMailMessage.setSubject("ToLand App - Recuperación de credenciales de usuario");
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

}
