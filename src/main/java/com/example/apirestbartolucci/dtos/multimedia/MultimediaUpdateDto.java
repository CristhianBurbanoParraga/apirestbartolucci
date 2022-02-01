/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.multimedia;

/**
 *
 * @author criss
 */
public class MultimediaUpdateDto {

    private long id;
    private long idContenido;
    private String descripcion;
    private String tipo;
    private boolean isInicial;
    private String publicid;
    private String url;

    public MultimediaUpdateDto() {
    }

    public MultimediaUpdateDto(long id, long idContenido,
            String descripcion, String tipo, boolean isInicial,
            String publicid, String url) {
        this.id = id;
        this.idContenido = idContenido;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.isInicial = isInicial;
        this.publicid = publicid;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isIsInicial() {
        return isInicial;
    }

    public void setIsInicial(boolean isInicial) {
        this.isInicial = isInicial;
    }

    public String getPublicid() {
        return publicid;
    }

    public void setPublicid(String publicid) {
        this.publicid = publicid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
