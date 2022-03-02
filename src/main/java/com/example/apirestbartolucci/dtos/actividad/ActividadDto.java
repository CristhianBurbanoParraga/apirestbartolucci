/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.actividad;

/**
 *
 * @author criss
 */
public class ActividadDto {

    private int id;
    private int idSubnivel;
    private String subnivel;
    private int idDocente;
    private String docente;
    private String nombre;
    private String descripcion;
    private int recompensavalor;
    private String tipo;
    private boolean activo;

    public ActividadDto() {
    }

    public ActividadDto(int id, int idSubnivel, String subnivel, int idDocente,
            String docente, String nombre, String descripcion, int recompensavalor,
            String tipo, boolean activo) {
        this.id = id;
        this.idSubnivel = idSubnivel;
        this.subnivel = subnivel;
        this.idDocente = idDocente;
        this.docente = docente;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recompensavalor = recompensavalor;
        this.tipo = tipo;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSubnivel() {
        return idSubnivel;
    }

    public void setIdSubnivel(int idSubnivel) {
        this.idSubnivel = idSubnivel;
    }

    public String getSubnivel() {
        return subnivel;
    }

    public void setSubnivel(String subnivel) {
        this.subnivel = subnivel;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
