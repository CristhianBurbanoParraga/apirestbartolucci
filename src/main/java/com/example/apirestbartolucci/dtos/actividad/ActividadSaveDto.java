/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.actividad;

import com.example.apirestbartolucci.dtos.multimedia.OtherMultimediaDto;

/**
 *
 * @author criss
 */
public class ActividadSaveDto {

    private int idSubnivel;
    private int idDocente;
    private String nombre;
    private String descripcion;
    private int recompensavalor;
    private String tipo;
    private OtherMultimediaDto multimedia;

    public ActividadSaveDto() {
    }

    public ActividadSaveDto(int idSubnivel, int idDocente, String nombre,
            String descripcion, int recompensavalor, String tipo,
            OtherMultimediaDto multimedia) {
        this.idSubnivel = idSubnivel;
        this.idDocente = idDocente;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recompensavalor = recompensavalor;
        this.tipo = tipo;
        this.multimedia = multimedia;
    }

    public int getIdSubnivel() {
        return idSubnivel;
    }

    public void setIdSubnivel(int idSubnivel) {
        this.idSubnivel = idSubnivel;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getRecompensavalor() {
        return recompensavalor;
    }

    public void setRecompensavalor(int recompensavalor) {
        this.recompensavalor = recompensavalor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public OtherMultimediaDto getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(OtherMultimediaDto multimedia) {
        this.multimedia = multimedia;
    }

}
