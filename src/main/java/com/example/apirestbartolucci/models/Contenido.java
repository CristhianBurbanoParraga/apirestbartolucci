/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author criss
 */
@Entity
@Table(name = "contenido")
public class Contenido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idactividad", nullable = false)
    @JsonBackReference
    private Actividad actividad;

    @Column(unique = false, nullable = true)
    private String descripcion;

    @Column(unique = false, nullable = false)
    private boolean enunciado;

    @Column(unique = false, nullable = false)
    private boolean respuesta;

    @Column(unique = false, nullable = false)
    private boolean activo;

    @OneToMany(mappedBy = "contenido", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Multimedia> multimedia;

    public Contenido() {
    }

    public Contenido(long id, Actividad actividad, String descripcion,
            boolean enunciado, boolean respuesta, boolean activo,
            Set<Multimedia> multimedia) {
        this.id = id;
        this.actividad = actividad;
        this.descripcion = descripcion;
        this.activo = activo;
        this.multimedia = multimedia;
        this.enunciado = enunciado;
        this.respuesta = respuesta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEnunciado() {
        return enunciado;
    }

    public void setEnunciado(boolean enunciado) {
        this.enunciado = enunciado;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Set<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }
}
