/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.historial;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author criss
 */
public class HistorialOtherDto {

    private long id;
    private int idEstudiante;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private int recompensaganada;

    public HistorialOtherDto() {
    }

    public HistorialOtherDto(long id, int idEstudiante, Date fecha,
            int recompensaganada) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.fecha = fecha;
        this.recompensaganada = recompensaganada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getRecompensaganada() {
        return recompensaganada;
    }

    public void setRecompensaganada(int recompensaganada) {
        this.recompensaganada = recompensaganada;
    }

}
