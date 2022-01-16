/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class JasyptService {

    @Autowired
    StringEncryptor stringEncryptor;

    public String EncryptValor(String valor) {
        return stringEncryptor.encrypt(valor);
    }

    public String DecryptValor(String valor) {
        return stringEncryptor.decrypt(valor);
    }

}
