/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.estudiante;

import com.example.apirestbartolucci.models.Grupo;
import com.example.apirestbartolucci.models.Inventario;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author criss
 */
public class EstudianteDto {

    private int id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechanacimiento;
    private int stockcaritas;
    private Set<Grupo> grupo;
    private Set<Inventario> inventario;

    public EstudianteDto() {
    }

    public EstudianteDto(int id, String nombres, String apellidos,
            String telefono, String correo, Date fechanacimiento, int stockcaritas,
            Set<Grupo> grupo, Set<Inventario> inventario) {
        this.id = id;
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
