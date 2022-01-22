/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.subnivel;

import com.example.apirestbartolucci.dtos.multimedia.OtherMultimediaDto;

/**
 *
 * @author criss
 */
public class SubnivelSaveDto {

    private int idNivel;
    private String nombre;
    private String descripcion;
    private OtherMultimediaDto multimedia;

    public SubnivelSaveDto() {
    }

    public SubnivelSaveDto(int idNivel, String nombre, String descripcion,
            OtherMultimediaDto multimedia) {
        this.idNivel = idNivel;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.multimedia = multimedia;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public OtherMultimediaDto getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(OtherMultimediaDto multimedia) {
        this.multimedia = multimedia;
    }

}
