/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.actividad;

import com.example.apirestbartolucci.dtos.historial.HistorialOtherDto;
import com.example.apirestbartolucci.models.Contenido;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author criss
 */
public class ActividadOtherDto {

    private int id;
    private String nombre;
    private String descripcion;
    private int recompensavalor;
    private String tipo;
    private boolean activo;
    private ArrayList<HistorialOtherDto> historial;
    private Set<Contenido> contenido;

    public ActividadOtherDto() {
    }

    public ActividadOtherDto(int id, String nombre, String descripcion,
            int recompensavalor, String tipo, boolean activo,
            ArrayList<HistorialOtherDto> historial, Set<Contenido> contenido) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recompensavalor = recompensavalor;
        this.tipo = tipo;
        this.activo = activo;
        this.historial = historial;
        this.contenido = contenido;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ArrayList<HistorialOtherDto> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<HistorialOtherDto> historial) {
        this.historial = historial;
    }

    public Set<Contenido> getContenido() {
        return contenido;
    }

    public void setContenido(Set<Contenido> contenido) {
        this.contenido = contenido;
    }
}
