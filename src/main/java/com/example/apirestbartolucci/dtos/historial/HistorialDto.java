/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.historial;

/**
 *
 * @author criss
 */
public class HistorialDto {

    private long id;
    private int idestudiante;
    private String estudiante;
    private int idactividad;
    private String nombre;
    private int recompensa;

    public HistorialDto() {
    }

    public HistorialDto(long id, int idestudiante, String estudiante,
            int idactividad, String nombre, int recompensa) {
        this.id = id;
        this.idestudiante = idestudiante;
        this.estudiante = estudiante;
        this.idactividad = idactividad;
        this.nombre = nombre;
        this.recompensa = recompensa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(int recompensa) {
        this.recompensa = recompensa;
    }

}
