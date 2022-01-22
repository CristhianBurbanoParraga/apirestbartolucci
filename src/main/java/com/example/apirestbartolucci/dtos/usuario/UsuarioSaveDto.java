/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.usuario;

import com.example.apirestbartolucci.dtos.estudiante.EstudianteSelectedDocenteDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author criss
 */
public class UsuarioSaveDto {

    private String usuario;
    private String clave;
    private boolean isDocente = false;
    private EstudianteSelectedDocenteDto selectedDocente;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechanacimiento;

    public UsuarioSaveDto() {
    }

    public UsuarioSaveDto(String usuario, String clave, boolean isDocente,
            EstudianteSelectedDocenteDto selectedDocente, String nombres,
            String apellidos, String telefono, String correo,
            Date fechanacimiento) {
        this.usuario = usuario;
        this.clave = clave;
        this.isDocente = isDocente;
        this.selectedDocente = selectedDocente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.fechanacimiento = fechanacimiento;
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

    public boolean isIsDocente() {
        return isDocente;
    }

    public void setIsDocente(boolean isDocente) {
        this.isDocente = isDocente;
    }

    public EstudianteSelectedDocenteDto getSelectedDocente() {
        return selectedDocente;
    }

    public void setSelectedDocente(
            EstudianteSelectedDocenteDto selectedDocente) {
        this.selectedDocente = selectedDocente;
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

}
