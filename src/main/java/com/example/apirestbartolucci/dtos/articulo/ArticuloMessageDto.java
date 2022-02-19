/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.articulo;

import com.example.apirestbartolucci.models.Articulo;
import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class ArticuloMessageDto {

    private boolean status;
    private String message;
    private Articulo articulo;
    private ArrayList<Articulo> articulos;

    public ArticuloMessageDto() {
    }

    public ArticuloMessageDto(boolean status, String message,
            Articulo articulo, ArrayList<Articulo> articulos) {
        this.status = status;
        this.message = message;
        this.articulo = articulo;
        this.articulos = articulos;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }
}
