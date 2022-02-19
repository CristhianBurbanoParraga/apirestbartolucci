/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.nivel;

import com.example.apirestbartolucci.models.Nivel;
import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class NivelMessageDto {

    private boolean status;
    private String message;
    private Nivel nivel;
    private ArrayList<Nivel> niveles;

    public NivelMessageDto() {
    }

    public NivelMessageDto(boolean status, String message, Nivel nivel,
            ArrayList<Nivel> niveles) {
        this.status = status;
        this.message = message;
        this.nivel = nivel;
        this.niveles = niveles;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public ArrayList<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(ArrayList<Nivel> niveles) {
        this.niveles = niveles;
    }
}
