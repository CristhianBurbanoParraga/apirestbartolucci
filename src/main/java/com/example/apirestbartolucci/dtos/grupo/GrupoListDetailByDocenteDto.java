/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.grupo;

/**
 *
 * @author criss
 */
public class GrupoListDetailByDocenteDto {

    private int id;
    private int idestudiante;
    private String estudiante;
    private String fecha;
    private boolean activo;

    public GrupoListDetailByDocenteDto() {
    }

    public GrupoListDetailByDocenteDto(int id, int idestudiante,
            String estudiante, String fecha, boolean activo) {
        this.id = id;
        this.idestudiante = idestudiante;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
