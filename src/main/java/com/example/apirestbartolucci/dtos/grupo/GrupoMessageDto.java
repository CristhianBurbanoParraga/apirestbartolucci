/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.grupo;

import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class GrupoMessageDto {

    private boolean status;
    private String message;
    private GrupoDto grupoDto;
    private GrupoListByDocenteDto ByDocenteDto;
    private ArrayList<GrupoListByDocenteDto> listByDocenteDto;

    public GrupoMessageDto() {
    }

    public GrupoMessageDto(boolean status, String message, GrupoDto grupoDto,
            GrupoListByDocenteDto ByDocenteDto,
            ArrayList<GrupoListByDocenteDto> listByDocenteDto) {
        this.status = status;
        this.message = message;
        this.grupoDto = grupoDto;
        this.ByDocenteDto = ByDocenteDto;
        this.listByDocenteDto = listByDocenteDto;
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

    public GrupoDto getGrupoDto() {
        return grupoDto;
    }

    public void setGrupoDto(GrupoDto grupoDto) {
        this.grupoDto = grupoDto;
    }

    public GrupoListByDocenteDto getByDocenteDto() {
        return ByDocenteDto;
    }

    public void setByDocenteDto(GrupoListByDocenteDto ByDocenteDto) {
        this.ByDocenteDto = ByDocenteDto;
    }

    public ArrayList<GrupoListByDocenteDto> getListByDocenteDto() {
        return listByDocenteDto;
    }

    public void setListByDocenteDto(
            ArrayList<GrupoListByDocenteDto> listByDocenteDto) {
        this.listByDocenteDto = listByDocenteDto;
    }
}
