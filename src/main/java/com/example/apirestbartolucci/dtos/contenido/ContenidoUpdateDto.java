/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.contenido;

/**
 *
 * @author criss
 */
public class ContenidoUpdateDto {

    private long id;
    private int idActividad;
    private String descripcion;
    private boolean activo;

    public ContenidoUpdateDto() {
    }

    public ContenidoUpdateDto(long id, int idActividad,
            String descripcion, boolean activo) {
        this.id = id;
        this.idActividad = idActividad;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
