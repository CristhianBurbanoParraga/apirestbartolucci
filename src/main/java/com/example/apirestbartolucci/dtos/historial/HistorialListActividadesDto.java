/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.historial;

/**
 *
 * @author criss
 */
public class HistorialListActividadesDto {

    private long id;
    private int idactividad;
    private String nombre;
    private int recompensa;

    public HistorialListActividadesDto() {
    }

    public HistorialListActividadesDto(long id, int idactividad, String nombre,
            int recompensa) {
        this.id = id;
        this.idactividad = idactividad;
        this.nombre = nombre;
        this.recompensa = recompensa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(int recompensa) {
        this.recompensa = recompensa;
    }
}
