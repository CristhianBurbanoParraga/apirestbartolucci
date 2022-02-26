/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.nivel;

import com.example.apirestbartolucci.dtos.subnivel.SubnivelDto;
import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class NivelDto {

    private int id;
    private String nombre;
    private String descripcion;
    private int prioridad;
    private String publicid;
    private String url;
    private boolean activo;
    private ArrayList<SubnivelDto> subniveles;

    public NivelDto() {
    }

    public NivelDto(int id, String nombre, String descripcion,
            int prioridad, String publicid, String url, boolean activo,
            ArrayList<SubnivelDto> subniveles) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.publicid = publicid;
        this.url = url;
        this.activo = activo;
        this.subniveles = subniveles;
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

    public ArrayList<SubnivelDto> getSubniveles() {
        return subniveles;
    }

    public void setSubniveles(ArrayList<SubnivelDto> subniveles) {
        this.subniveles = subniveles;
    }

}
