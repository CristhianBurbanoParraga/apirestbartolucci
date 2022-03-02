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
    private ArrayList<ActividadListTypeEvaluativaDto> actividadesEv;
    private ArrayList<Actividad> actividades;
    private ArrayList<ActividadDto> actividadesDtos;

    public ActividadMessageDto() {
    }

    public ActividadMessageDto(boolean status, String message,
            Actividad actividad, ArrayList<ActividadListTypeEvaluativaDto> actividadesEv,
            ArrayList<Actividad> actividades, ArrayList<ActividadDto> actividadesDtos) {
        this.status = status;
        this.message = message;
        this.actividad = actividad;
        this.actividadesEv = actividadesEv;
        this.actividades = actividades;
        this.actividadesDtos = actividadesDtos;
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

    public ArrayList<ActividadListTypeEvaluativaDto> getActividadesEv() {
        return actividadesEv;
    }

    public void setActividadesEv(ArrayList<ActividadListTypeEvaluativaDto> actividadesEv) {
        this.actividadesEv = actividadesEv;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    public ArrayList<ActividadDto> getActividadesDtos() {
        return actividadesDtos;
    }

    public void setActividadesDtos(ArrayList<ActividadDto> actividadesDtos) {
        this.actividadesDtos = actividadesDtos;
    }

}
