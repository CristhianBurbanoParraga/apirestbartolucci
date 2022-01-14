/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.models;

/**
 *
 * @author criss
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@Entity
@Table(name = "actividad")
public class Actividad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idsubnivel", nullable = false)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Subnivel subnivel;

    private String tipo;

    private String detalle;

    private int valor;

    private boolean activo;

    @OneToMany(mappedBy = "actividad", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Contenido> contenido;

    public Actividad() {
    }

    public Actividad(int id, Subnivel subnivel, String tipo,
            String detalle, int valor, boolean activo) {
        this.id = id;
        this.subnivel = subnivel;
        this.tipo = tipo;
        this.detalle = detalle;
        this.valor = valor;
        this.activo = activo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
