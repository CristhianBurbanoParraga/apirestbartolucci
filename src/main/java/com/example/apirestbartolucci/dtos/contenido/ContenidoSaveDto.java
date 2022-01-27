/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.contenido;

/**
 *
 * @author criss
 */
public class ContenidoSaveDto {

    private int idActividad;
    private String descripcion;
    private boolean isEnunciado;
    private boolean isRespuesta;

    public ContenidoSaveDto() {
    }

    public ContenidoSaveDto(int idActividad, String descripcion,
            boolean isEnunciado, boolean isRespuesta) {
        this.idActividad = idActividad;
        this.descripcion = descripcion;
        this.isEnunciado = isEnunciado;
        this.isRespuesta = isRespuesta;
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

    public boolean isIsRespuesta() {
        return isRespuesta;
    }

    public void setIsRespuesta(boolean isRespuesta) {
        this.isRespuesta = isRespuesta;
    }
}
