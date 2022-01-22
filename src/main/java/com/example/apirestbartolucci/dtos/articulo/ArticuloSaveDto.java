/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.articulo;

import com.example.apirestbartolucci.dtos.multimedia.OtherMultimediaDto;

/**
 *
 * @author criss
 */
public class ArticuloSaveDto {

    private String nombre;
    private int costo;
    private OtherMultimediaDto multimedia;

    public ArticuloSaveDto() {
    }

    public ArticuloSaveDto(String nombre, int costo,
            OtherMultimediaDto multimedia) {
        this.nombre = nombre;
        this.costo = costo;
        this.multimedia = multimedia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public OtherMultimediaDto getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(OtherMultimediaDto multimedia) {
        this.multimedia = multimedia;
    }

}
