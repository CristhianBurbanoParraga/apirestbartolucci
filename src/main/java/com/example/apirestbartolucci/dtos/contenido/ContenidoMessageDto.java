/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.contenido;

import com.example.apirestbartolucci.models.Contenido;
import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class ContenidoMessageDto {

    private boolean status;
    private String message;
    private Contenido contenido;
    private ArrayList<Contenido> contenidos;

    public ContenidoMessageDto() {
    }

    public ContenidoMessageDto(boolean status, String message,
            Contenido contenido, ArrayList<Contenido> contenidos) {
        this.status = status;
        this.message = message;
        this.contenido = contenido;
        this.contenidos = contenidos;
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

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public ArrayList<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(ArrayList<Contenido> contenidos) {
        this.contenidos = contenidos;
    }
}
