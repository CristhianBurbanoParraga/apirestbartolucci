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
public class ActividadListTypeEvaluativaDto {

    private int idNivel;
    private String nombre;
    private ArrayList<Actividad> actividades;

    public ActividadListTypeEvaluativaDto() {
    }

    public ActividadListTypeEvaluativaDto(int idNivel, String nombre,
            ArrayList<Actividad> actividades) {
        this.idNivel = idNivel;
        this.nombre = nombre;
        this.actividades = actividades;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

}
