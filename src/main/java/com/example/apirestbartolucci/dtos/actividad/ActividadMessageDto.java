/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.actividad;

import com.example.apirestbartolucci.models.Actividad;
import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class ActividadMessageDto {

    private boolean status;
    private String message;
    private Actividad actividad;
    private ArrayList<Actividad> actividades;

    public ActividadMessageDto() {
    }

    public ActividadMessageDto(boolean status, String message,
            Actividad actividad, ArrayList<Actividad> actividades) {
        this.status = status;
        this.message = message;
        this.actividad = actividad;
        this.actividades = actividades;
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

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

}
