/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.historial;

import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class HistorialListDto {

    private int idestudiante;
    private String estudiante;
    private ArrayList<HistorialListActividadesDto> actividadesCompletas;

    public HistorialListDto() {
    }

    public HistorialListDto(int idestudiante, String estudiante,
            ArrayList<HistorialListActividadesDto> actividadesCompletas) {
        this.idestudiante = idestudiante;
        this.estudiante = estudiante;
        this.actividadesCompletas = actividadesCompletas;
    }

    public int getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(int idestudiante) {
        this.idestudiante = idestudiante;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public ArrayList<HistorialListActividadesDto> getActividadesCompletas() {
        return actividadesCompletas;
    }

    public void setActividadesCompletas(
            ArrayList<HistorialListActividadesDto> actividadesCompletas) {
        this.actividadesCompletas = actividadesCompletas;
    }

}
