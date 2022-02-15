/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class TwilioService {

    public static final String ACCOUNT_SID = "ACa16f376761a342fbc22ca9863b6d0452";
    public static final String AUTH_TOKEN = "aedd"
            + "2a09dbb876f29f1f7a0a52b37b73";
    public static final String TRIAL_NUMBER = "+18596953761";

    public TwilioService() {
    }

    public String SendSMS(String phone, String mensaje) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(new PhoneNumber("+593" + phone),
                    new PhoneNumber(TRIAL_NUMBER),
                    mensaje).create();
            return "SMS enviado con exito, si no recive el SMS en un lapso "
                    + "de 15 a 30 min vuelva a intentar";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

}
