/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.actividad.ActividadSaveDto;
import com.example.apirestbartolucci.dtos.actividad.ActividadUpdateDto;
import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Docente;
import com.example.apirestbartolucci.models.Subnivel;
import com.example.apirestbartolucci.repositories.ActividadRepository;
import com.example.apirestbartolucci.repositories.DocenteRepository;
import com.example.apirestbartolucci.repositories.SubnivelRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class ActividadService {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    SubnivelRepository subnivelRepository;

    @Autowired
    DocenteRepository docenteRepository;

    public ArrayList<Actividad> GetAllActividades() {
        return (ArrayList<Actividad>) actividadRepository.findAll();
    }

    public Optional<Actividad> GetActividadById(int id) {
        return actividadRepository.findById(id);
    }

    public ArrayList<Actividad> GetActividadByIdSubnivel(int idSubnivel) {
        Optional<Subnivel> subnivel = subnivelRepository.findById(idSubnivel);
        if (subnivel.isPresent()) {
            return actividadRepository.findBySubnivel(subnivel.get());
        } else {
            return new ArrayList<Actividad>();
        }
    }

    public ArrayList<Actividad> GetActividadByIdDocente(int idDocente) {
        Optional<Docente> docente = docenteRepository.findById(idDocente);
        if (docente.isPresent()) {
            return actividadRepository.findByDocente(docente.get());
        } else {
            return new ArrayList<Actividad>();
        }
    }

    public ArrayList<Actividad> GetActividadBySubnivelAndDocente(
            int idSubnivel, int idDocente) {
        Optional<Subnivel> subnivel = subnivelRepository.findById(idSubnivel);
        if (subnivel.isPresent()) {
            Optional<Docente> docente = docenteRepository.findById(idDocente);
            if (docente.isPresent()) {
                return actividadRepository.findBySubnivelAndDocente(
                        subnivel.get(), docente.get());
            } else {
                return new ArrayList<Actividad>();
            }
        } else {
            return new ArrayList<Actividad>();
        }
    }

    public Optional<Actividad> GetActividadByNombre(String nombre) {
        return actividadRepository.findByNombre(nombre);
    }

    public ArrayList<Actividad> GetActividadByStatus(boolean activo) {
        return actividadRepository.findByActivo(activo);
    }

    public Actividad SaveActividad(ActividadSaveDto actividadSaveDto) {
        Optional<Subnivel> subnivel = subnivelRepository
                .findById(actividadSaveDto.getIdSubnivel());
        if (subnivel.isPresent()) {
            Optional<Docente> docente = docenteRepository.findById(
                    actividadSaveDto.getIdDocente());
            if (docente.isPresent()) {
                int count = 0;
                if (actividadSaveDto.getTipo().equals("EV")) {
                    ArrayList<Actividad> actividades = actividadRepository
                            .findBySubnivelAndDocente(subnivel.get(), docente.get());
                    for (int i = 0; i < actividades.size(); i++) {
                        if (actividades.get(i).getTipo().equals("EV")) {
                            count++;
                        }
                    }
                }
                if (count >= 1) {
                    return null;
                } else {
                    subnivel.get().setNumactividades(
                            subnivel.get().getNumactividades() + 1);
                    Actividad actividad = new Actividad(0,
                            subnivel.get(),
                            docente.get(),
                            actividadSaveDto.getNombre(),
                            actividadSaveDto.getDescripcion(),
                            actividadSaveDto.getRecompensavalor(),
                            actividadSaveDto.getTipo(),
                            true, null, null);
                    subnivelRepository.save(subnivel.get());
                    actividadRepository.save(actividad);
                    return actividad;
                }

            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Actividad UpdateActividad(ActividadUpdateDto actividadUpdateDto) {
        Optional<Actividad> actividad
                = actividadRepository.findById(actividadUpdateDto.getId());
        if (actividad.isPresent()) {
            Optional<Subnivel> subnivel = subnivelRepository
                    .findById(actividadUpdateDto.getIdSubnivel());
            if (subnivel.isPresent()) {
                actividad.get().setSubnivel(subnivel.get());
                actividad.get().setNombre(actividadUpdateDto.getNombre());
                actividad.get().setDescripcion(
                        actividadUpdateDto.getDescripcion());
                actividad.get().setRecompensavalor(
                        actividadUpdateDto.getRecompensavalor());
                actividad.get().setTipo(actividadUpdateDto.getTipo());
                actividad.get().setActivo(actividadUpdateDto.isActivo());
                return actividadRepository.save(actividad.get());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
