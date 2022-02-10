/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.grupo;

import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class GrupoListByDocenteDto {

    private int iddocente;
    private String docente;
    private ArrayList<GrupoListDetailByDocenteDto> estudiantes;

    public GrupoListByDocenteDto() {
    }

    public GrupoListByDocenteDto(int iddocente, String docente,
            ArrayList<GrupoListDetailByDocenteDto> estudiantes) {
        this.iddocente = iddocente;
        this.docente = docente;
        this.estudiantes = estudiantes;
    }

    public int getIddocente() {
        return iddocente;
    }

    public void setIddocente(int iddocente) {
        this.iddocente = iddocente;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public ArrayList<GrupoListDetailByDocenteDto> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<GrupoListDetailByDocenteDto> estudiantes) {
        this.estudiantes = estudiantes;
    }

}
