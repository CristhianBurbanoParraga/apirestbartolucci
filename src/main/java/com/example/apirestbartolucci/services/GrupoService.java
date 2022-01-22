/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.models.Docente;
import com.example.apirestbartolucci.models.Estudiante;
import com.example.apirestbartolucci.models.Grupo;
import com.example.apirestbartolucci.repositories.DocenteRepository;
import com.example.apirestbartolucci.repositories.EstudianteRepository;
import com.example.apirestbartolucci.repositories.GrupoRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class GrupoService {

    @Autowired
    GrupoRepository grupoRepository;

    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    EstudianteRepository estudianteRepository;

    public ArrayList<Grupo> GetAllGrupos() {
        return (ArrayList<Grupo>) grupoRepository.findAll();
    }

    public Optional<Grupo> GetGrupoById(int id) {
        return grupoRepository.findById(id);
    }

    public ArrayList<Grupo> GetGruposByFecharegistro(Date fecharegistro) {
        return (ArrayList<Grupo>) grupoRepository
                .findByFecharegistro(fecharegistro);
    }

    public ArrayList<Grupo> GetGrupoByIdDocente(int id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if (docente.isPresent()) {
            return grupoRepository.findByDocente(docente.get());
        } else {
            return new ArrayList<Grupo>();
        }
    }

    public ArrayList<Grupo> GetGrupoByIdEstudiante(int id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isPresent()) {
            return grupoRepository.findByEstudiante(estudiante.get());
        } else {
            return new ArrayList<Grupo>();
        }
    }

    public String ChangeStatusGrupo(int id, boolean activo) {
        Optional<Grupo> grupo = grupoRepository.findById(id);
        if (grupo.isPresent()) {
            grupo.get().setActivo(activo);
            grupoRepository.save(grupo.get());
            return "Grupo con id: "
                    + String.valueOf(id) + " cambiada a: "
                    + String.valueOf(activo);
        } else {
            return "Grupo con id: " + String.valueOf(id) + "inexistente";
        }
    }

}
