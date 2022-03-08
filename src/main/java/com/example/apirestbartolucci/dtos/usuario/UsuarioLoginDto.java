/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.usuario;

import com.example.apirestbartolucci.dtos.docente.DocenteDto;
import com.example.apirestbartolucci.dtos.estudiante.EstudianteDto;

/**
 *
 * @author criss
 */
public class UsuarioLoginDto {

    private int id;
    private String usuario;
    private String clave;
    private String tipousuario;
    private boolean activo;
    private DocenteDto docente;
    private EstudianteDto estudiante;

    public UsuarioLoginDto() {
    }

    public UsuarioLoginDto(int id, String usuario, String clave,
            String tipousuario, boolean activo, DocenteDto docente,
            EstudianteDto estudiante) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.tipousuario = tipousuario;
        this.activo = activo;
        this.docente = docente;
        this.estudiante = estudiante;
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

    public DocenteDto getDocente() {
        return docente;
    }

    public void setDocente(DocenteDto docente) {
        this.docente = docente;
    }

    public EstudianteDto getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDto estudiante) {
        this.estudiante = estudiante;
    }

}
