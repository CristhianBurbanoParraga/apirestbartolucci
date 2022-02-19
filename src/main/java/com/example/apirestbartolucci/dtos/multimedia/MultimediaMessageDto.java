/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.multimedia;

import com.example.apirestbartolucci.models.Multimedia;
import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class MultimediaMessageDto {

    private boolean status;
    private String message;
    private Multimedia multimedia;
    private OtherMultimediaDto otherMultimediaDto;
    private ArrayList<Multimedia> multimedias;

    public MultimediaMessageDto() {
    }

    public MultimediaMessageDto(boolean status, String message,
            Multimedia multimedia, OtherMultimediaDto otherMultimediaDto,
            ArrayList<Multimedia> multimedias) {
        this.status = status;
        this.message = message;
        this.multimedia = multimedia;
        this.otherMultimediaDto = otherMultimediaDto;
        this.multimedias = multimedias;
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

    public Multimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }

    public OtherMultimediaDto getOtherMultimediaDto() {
        return otherMultimediaDto;
    }

    public void setOtherMultimediaDto(OtherMultimediaDto otherMultimediaDto) {
        this.otherMultimediaDto = otherMultimediaDto;
    }

    public ArrayList<Multimedia> getMultimedias() {
        return multimedias;
    }

    public void setMultimedias(ArrayList<Multimedia> multimedias) {
        this.multimedias = multimedias;
    }
}
