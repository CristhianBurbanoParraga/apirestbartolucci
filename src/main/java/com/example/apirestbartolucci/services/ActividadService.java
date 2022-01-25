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

    public Optional<Actividad> GetActividadByNombre(String nombre) {
        return actividadRepository.findByNombre(nombre);
    }

    public ArrayList<Actividad> GetActividadByStatus(boolean activo) {
        return actividadRepository.findByActivo(activo);
    }

    public Actividad SaveActividad(ActividadSaveDto actividadSaveDto) {
        if (actividadSaveDto.getMultimedia().getPublicid() == null
                || actividadSaveDto.getMultimedia().getUrl() == null) {
            return null;
        } else {
            Optional<Subnivel> subnivel = subnivelRepository
                    .findById(actividadSaveDto.getIdSubnivel());
            if (subnivel.isPresent()) {
                Optional<Docente> docente = docenteRepository.findById(
                        actividadSaveDto.getIdDocente());
                if (docente.isPresent()) {
                    if (actividadRepository.findByDocenteAndNombre(
                            docente.get(), actividadSaveDto.getNombre())
                            .isPresent()) {
                        return null;
                    } else {
                        Actividad actividad = new Actividad(0,
                                subnivel.get(),
                                docente.get(),
                                actividadSaveDto.getNombre(),
                                actividadSaveDto.getDescripcion(),
                                actividadSaveDto.getRecompensavalor(),
                                actividadSaveDto.getTipo(),
                                actividadSaveDto.getMultimedia().getPublicid(),
                                actividadSaveDto.getMultimedia().getUrl(),
                                true, null, null);
                        return actividadRepository.save(actividad);
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
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
