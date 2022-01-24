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
@Table(name = "actividad")
public class Actividad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idsubnivel", nullable = false)
    @JsonBackReference
    private Subnivel subnivel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "iddocente", nullable = false)
    @JsonBackReference
    private Docente docente;

    @Column(length = 50, nullable = false, unique = false)
    private String nombre;

    @Column(nullable = true, unique = false)
    private String descripcion;

    @Column(nullable = false, unique = false)
    private int recompensavalor;

    @Column(length = 2, nullable = false, unique = false)
    private String tipo;

    @Column(length = 30, nullable = false, unique = true)
    private String publicid;

    @Column(nullable = false, unique = false)
    private String url;

    @Column(nullable = false, unique = false)
    private boolean activo;

    @OneToMany(mappedBy = "actividad", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Historial> historial;

    public Actividad() {
    }

    public Actividad(int id, Subnivel subnivel, Docente docente,
            String nombre, String descripcion, int recompensavalor, String tipo,
            String publicid, String url, boolean activo,
            Set<Historial> historial) {
        this.id = id;
        this.subnivel = subnivel;
        this.docente = docente;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recompensavalor = recompensavalor;
        this.tipo = tipo;
        this.publicid = publicid;
        this.url = url;
        this.activo = activo;
        this.historial = historial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subnivel getSubnivel() {
        return subnivel;
    }

    public void setSubnivel(Subnivel subnivel) {
        this.subnivel = subnivel;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getRecompensavalor() {
        return recompensavalor;
    }

    public void setRecompensavalor(int recompensavalor) {
        this.recompensavalor = recompensavalor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPublicid() {
        return publicid;
    }

    public void setPublicid(String publicid) {
        this.publicid = publicid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<Historial> getHistorial() {
        return historial;
    }

    public void setHistorial(Set<Historial> historial) {
        this.historial = historial;
    }

}
