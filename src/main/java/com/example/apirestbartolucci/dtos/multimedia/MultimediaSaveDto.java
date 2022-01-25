/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.multimedia;

/**
 *
 * @author criss
 */
public class MultimediaSaveDto {

    private long idContenido;
    private String descripcion;
    private String tipo;
    private OtherMultimediaDto multimedia;

    public MultimediaSaveDto() {
    }

    public MultimediaSaveDto(long idContenido, String descripcion,
            String tipo, OtherMultimediaDto multimedia) {
        this.idContenido = idContenido;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.multimedia = multimedia;
    }

    public long getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(long idContenido) {
        this.idContenido = idContenido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
