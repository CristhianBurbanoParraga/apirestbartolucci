/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.inventario;

import com.example.apirestbartolucci.models.Inventario;
import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class InventarioMessageDto {

    private boolean status;
    private String message;
    private Inventario inventario;
    private ArrayList<Inventario> inventarios;

    public InventarioMessageDto() {
    }

    public InventarioMessageDto(boolean status, String message,
            Inventario inventario, ArrayList<Inventario> inventarios) {
        this.status = status;
        this.message = message;
        this.inventario = inventario;
        this.inventarios = inventarios;
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

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public ArrayList<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(ArrayList<Inventario> inventarios) {
        this.inventarios = inventarios;
    }
}
