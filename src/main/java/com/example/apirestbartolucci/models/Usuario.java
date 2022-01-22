/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.models;

/**
 *
 * @author criss
 */
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(length = 15, nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false, unique = false)
    private String clave;

    @Column(length = 2, nullable = false, unique = false)
    private String tipousuario;

    @Column(nullable = false, unique = false)
    private boolean activo;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    //@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Estudiante estudiante;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    //@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Docente docente;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String clave,
            String tipousuario, boolean activo, Estudiante estudiante,
            Docente docente) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.tipousuario = tipousuario;
        this.activo = activo;
        this.estudiante = estudiante;
        this.docente = docente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

}
