/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.historial;

import com.example.apirestbartolucci.models.Historial;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class HistorialMessageDto {

    private boolean status;
    private String message;
    private Historial historial;
    private HistorialDto historialDto;
    private HistorialListDto listDto;
    private ArrayList<HistorialListDto> listActividadesDto;

    public HistorialMessageDto() {
    }

    public HistorialMessageDto(boolean status, String message, Historial historial,
            HistorialDto historialDto, HistorialListDto listDto,
            ArrayList<HistorialListDto> listActividadesDto) {
        this.status = status;
        this.message = message;
        this.historial = historial;
        this.historialDto = historialDto;
        this.listDto = listDto;
        this.listActividadesDto = listActividadesDto;
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

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public HistorialDto getHistorialDto() {
        return historialDto;
    }

    public void setHistorialDto(HistorialDto historialDto) {
        this.historialDto = historialDto;
    }

    public HistorialListDto getListDto() {
        return listDto;
    }

    public void setListDto(HistorialListDto listDto) {
        this.listDto = listDto;
    }

    public ArrayList<HistorialListDto> getListActividadesDto() {
        return listActividadesDto;
    }

    public void setListActividadesDto(
            ArrayList<HistorialListDto> listActividadesDto) {
        this.listActividadesDto = listActividadesDto;
    }
}
