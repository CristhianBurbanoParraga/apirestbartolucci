/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.type.PhoneNumber;
import java.util.HashMap;
import java.util.Map;
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

    public Map<String, String> ValidationTwilio(String phone) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ValidationRequest validationRequest = ValidationRequest.creator(
                new com.twilio.type.PhoneNumber("+593" + phone))
                .setFriendlyName("Validation phone with number +593" + phone)
                .create();
        Map<String, String> mapValues = new HashMap<>();
        mapValues.put("account_sid", validationRequest.getAccountSid());
        mapValues.put("call_sid", validationRequest.getCallSid());
        mapValues.put("friendly_name", validationRequest.getFriendlyName());
        mapValues.put("phone_number", validationRequest.getPhoneNumber().toString());
        mapValues.put("validation_code", validationRequest.getValidationCode());
        return mapValues;
    }

}
