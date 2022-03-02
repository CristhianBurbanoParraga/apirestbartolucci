/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.subnivel;

import com.example.apirestbartolucci.models.Subnivel;
import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class SubnivelMessageDto {

    private boolean status;
    private String message;
    private Subnivel subnivel;
    private ArrayList<Subnivel> subniveles;
    private ArrayList<SubnivelTwoVersionDto> subnivelesDto;

    public SubnivelMessageDto() {
    }

    public SubnivelMessageDto(boolean status, String message, Subnivel subnivel,
            ArrayList<Subnivel> subniveles, ArrayList<SubnivelTwoVersionDto> subnivelesDto) {
        this.status = status;
        this.message = message;
        this.subnivel = subnivel;
        this.subniveles = subniveles;
        this.subnivelesDto = subnivelesDto;
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

    public Subnivel getSubnivel() {
        return subnivel;
    }

    public void setSubnivel(Subnivel subnivel) {
        this.subnivel = subnivel;
    }

    public ArrayList<Subnivel> getSubniveles() {
        return subniveles;
    }

    public void setSubniveles(ArrayList<Subnivel> subniveles) {
        this.subniveles = subniveles;
    }

    public ArrayList<SubnivelTwoVersionDto> getSubnivelesDto() {
        return subnivelesDto;
    }

    public void setSubnivelesDto(ArrayList<SubnivelTwoVersionDto> subnivelesDto) {
        this.subnivelesDto = subnivelesDto;
    }
}
