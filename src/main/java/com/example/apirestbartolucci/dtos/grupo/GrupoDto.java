/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.grupo;

/**
 *
 * @author criss
 */
public class GrupoDto {

    private int id;
    private int iddocente;
    private String docente;
    private int idestudiante;
    private String estudiante;
    private String fecha;
    private boolean activo;

    public GrupoDto() {
    }

    public GrupoDto(int id, int iddocente, String docente, int idestudiante,
            String estudiante, String fecha, boolean activo) {
        this.id = id;
        this.iddocente = iddocente;
        this.docente = docente;
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

    public int getIddocente() {
        return iddocente;
    }

    public void setIddocente(int iddocente) {
        this.iddocente = iddocente;
    }

    public int getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(int idestudiante) {
        this.idestudiante = idestudiante;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
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
