/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.subnivel;

/**
 *
 * @author criss
 */
public class SubnivelDto {

    private int id;
    private String nombre;
    private String descripcion;
    private int numactividades;
    private int prioridad;
    private String publicid;
    private String url;
    private boolean activo;

    public SubnivelDto() {
    }

    public SubnivelDto(int id, String nombre, String descripcion,
            int numactividades, int prioridad, String publicid,
            String url, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numactividades = numactividades;
        this.prioridad = prioridad;
        this.publicid = publicid;
        this.url = url;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumactividades() {
        return numactividades;
    }

    public void setNumactividades(int numactividades) {
        this.numactividades = numactividades;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
