/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.historial;

import java.util.Map;

/**
 *
 * @author criss
 */
public class HistorialSaveDto {

    private int idEstudiante;
    private int idActividad;
    private boolean statusRespuesta;
    private Map<String, Long> idsContenido;

    public HistorialSaveDto() {
    }

    public HistorialSaveDto(int idEstudiante, int idActividad,
            boolean statusRespuesta, Map<String, Long> idsContenido) {
        this.idEstudiante = idEstudiante;
        this.idActividad = idActividad;
        this.statusRespuesta = statusRespuesta;
        this.idsContenido = idsContenido;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public boolean isStatusRespuesta() {
        return statusRespuesta;
    }

    public void setStatusRespuesta(boolean statusRespuesta) {
        this.statusRespuesta = statusRespuesta;
    }

    public Map<String, Long> getIdsContenido() {
        return idsContenido;
    }

    public void setIdsContenido(Map<String, Long> idsContenido) {
        this.idsContenido = idsContenido;
    }

    

}
