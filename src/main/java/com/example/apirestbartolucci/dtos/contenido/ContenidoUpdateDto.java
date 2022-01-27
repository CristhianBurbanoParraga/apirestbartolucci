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
    private boolean isEnunciado;
    private boolean isRespuesta;
    private boolean activo;

    public ContenidoUpdateDto() {
    }

    public ContenidoUpdateDto(long id, int idActividad,
            String descripcion, boolean isEnunciado, boolean isRespuesta,
            boolean activo) {
        this.id = id;
        this.idActividad = idActividad;
        this.descripcion = descripcion;
        this.activo = activo;
        this.isEnunciado = isEnunciado;
        this.isRespuesta = isRespuesta;
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

    public boolean isIsEnunciado() {
        return isEnunciado;
    }

    public void setIsEnunciado(boolean isEnunciado) {
        this.isEnunciado = isEnunciado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isIsRespuesta() {
        return isRespuesta;
    }

    public void setIsRespuesta(boolean isRespuesta) {
        this.isRespuesta = isRespuesta;
    }

}
