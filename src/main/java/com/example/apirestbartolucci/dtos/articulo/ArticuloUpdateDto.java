/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.articulo;

/**
 *
 * @author criss
 */
public class ArticuloUpdateDto {

    private int id;
    private String nombre;
    private int costo;
    private boolean activo;

    public ArticuloUpdateDto() {
    }

    public ArticuloUpdateDto(int id, String nombre, int costo,
            boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
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

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
