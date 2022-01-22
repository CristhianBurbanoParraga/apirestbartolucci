/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author criss
 */
@Entity
@Table(name = "estudiante")
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idusuario", referencedColumnName = "id",
            nullable = false)
    @JsonBackReference
    //@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @Column(length = 50, unique = false, nullable = false)
    private String nombres;

    @Column(length = 50, unique = false, nullable = false)
    private String apellidos;

    @Column(length = 10, unique = true, nullable = false)
    private String telefono;

    @Column(length = 100, unique = true, nullable = false)
    private String correo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(unique = false, nullable = false)
    private Date fechanacimiento;

    @Column(unique = false, nullable = false)
    private int stockcaritas;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    //@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Set<Grupo> grupo;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Inventario> inventario;

    public Estudiante() {
    }

    public Estudiante(int id, Usuario usuario, String nombres,
            String apellidos, String telefono, String correo,
            Date fechanacimiento, int stockcaritas, Set<Grupo> grupo,
            Set<Inventario> inventario) {
        this.id = id;
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.fechanacimiento = fechanacimiento;
        this.stockcaritas = stockcaritas;
        this.grupo = grupo;
        this.inventario = inventario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public int getStockcaritas() {
        return stockcaritas;
    }

    public void setStockcaritas(int stockcaritas) {
        this.stockcaritas = stockcaritas;
    }

    public Set<Grupo> getGrupo() {
        return grupo;
    }

    public void setGrupo(Set<Grupo> grupo) {
        this.grupo = grupo;
    }

    public Set<Inventario> getInventario() {
        return inventario;
    }

    public void setInventario(Set<Inventario> inventario) {
        this.inventario = inventario;
    }

}
