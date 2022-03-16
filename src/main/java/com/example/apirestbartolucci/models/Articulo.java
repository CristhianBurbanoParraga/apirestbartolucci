/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.models;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author criss
 */
@Entity
@Table(name = "articulo")
public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(length = 50, unique = true, nullable = false)
    private String nombre;

    @Column(unique = false, nullable = false)
    private int costo;

    @Column(length = 35, nullable = false, unique = true)
    private String publicid;

    @Column(nullable = false, unique = false)
    private String url;

    @Column(nullable = false, unique = false)
    private boolean activo;

    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Inventario> inventario;

    public Articulo() {
    }

    public Articulo(int id, String nombre, int costo, String publicid,
            String url, boolean activo, Set<Inventario> inventario) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.publicid = publicid;
        this.url = url;
        this.activo = activo;
        this.inventario = inventario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
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

    public Set<Inventario> getInventario() {
        return inventario;
    }

    public void setInventario(Set<Inventario> inventario) {
        this.inventario = inventario;
    }
}
