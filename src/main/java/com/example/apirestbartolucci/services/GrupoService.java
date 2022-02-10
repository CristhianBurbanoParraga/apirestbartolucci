/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.grupo.GrupoDto;
import com.example.apirestbartolucci.dtos.grupo.GrupoListByDocenteDto;
import com.example.apirestbartolucci.dtos.grupo.GrupoListDetailByDocenteDto;
import com.example.apirestbartolucci.models.Docente;
//import com.example.apirestbartolucci.models.Estudiante;
import com.example.apirestbartolucci.models.Grupo;
import com.example.apirestbartolucci.repositories.DocenteRepository;
import com.example.apirestbartolucci.repositories.EstudianteRepository;
import com.example.apirestbartolucci.repositories.GrupoRepository;
import java.util.ArrayList;
//import java.util.Date;
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

    public ArrayList<GrupoDto> GetAllGrupos() {
        ArrayList<Grupo> grupos = (ArrayList<Grupo>) grupoRepository.findAll();
        ArrayList<GrupoDto> gruposDto = new ArrayList<GrupoDto>();
        for (int i = 0; i < grupos.size(); i++) {
            GrupoDto gdto = new GrupoDto(grupos.get(i).getId(),
                    grupos.get(i).getDocente().getId(),
                    grupos.get(i).getDocente().getNombres() + " "
                    + grupos.get(i).getDocente().getApellidos(),
                    grupos.get(i).getEstudiante().getId(),
                    grupos.get(i).getEstudiante().getNombres() + " "
                    + grupos.get(i).getEstudiante().getApellidos(),
                    grupos.get(i).getFecharegistro().toString(),
                    grupos.get(i).isActivo());
            gruposDto.add(gdto);
        }
        return gruposDto;
    }

    public GrupoDto GetGrupoById(int id) {
        Optional<Grupo> grupo = grupoRepository.findById(id);
        if (grupo.isPresent()) {
            GrupoDto grupoDto = new GrupoDto(grupo.get().getId(),
                    grupo.get().getDocente().getId(),
                    grupo.get().getDocente().getNombres() + " "
                    + grupo.get().getDocente().getApellidos(),
                    grupo.get().getEstudiante().getId(),
                    grupo.get().getEstudiante().getNombres() + " "
                    + grupo.get().getEstudiante().getApellidos(),
                    grupo.get().getFecharegistro().toString(),
                    grupo.get().isActivo());
            return grupoDto;
        } else {
            return null;
        }
    }

    /*public ArrayList<Grupo> GetGruposByFecharegistro(Date fecharegistro) {
        return (ArrayList<Grupo>) grupoRepository
                .findByFecharegistro(fecharegistro);
    }*/
    public GrupoListByDocenteDto GetGrupoByIdDocente(int id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if (docente.isPresent()) {
            GrupoListByDocenteDto listByDocente = new GrupoListByDocenteDto();
            listByDocente.setIddocente(id);
            listByDocente.setDocente(docente.get().getNombres() + " "
                    + docente.get().getApellidos());
            ArrayList<Grupo> grupos = grupoRepository.findByDocente(docente.get());
            ArrayList<GrupoListDetailByDocenteDto> detail
                    = new ArrayList<GrupoListDetailByDocenteDto>();
            for (int i = 0; i < grupos.size(); i++) {
                GrupoListDetailByDocenteDto item
                        = new GrupoListDetailByDocenteDto(grupos.get(i).getId(),
                                grupos.get(i).getEstudiante().getId(),
                                grupos.get(i).getEstudiante().getNombres() + " "
                                + grupos.get(i).getEstudiante().getApellidos(),
                                grupos.get(i).getFecharegistro().toString(),
                                grupos.get(i).isActivo());
                detail.add(item);
            }
            listByDocente.setEstudiantes(detail);
            return listByDocente;
        } else {
            return null;
        }
    }

    /*public ArrayList<Grupo> GetGrupoByIdEstudiante(int id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isPresent()) {
            return grupoRepository.findByEstudiante(estudiante.get());
        } else {
            return new ArrayList<Grupo>();
        }
    }*/

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
