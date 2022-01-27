/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.contenido.ContenidoSaveDto;
import com.example.apirestbartolucci.dtos.contenido.ContenidoUpdateDto;
import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Contenido;
import com.example.apirestbartolucci.repositories.ActividadRepository;
import com.example.apirestbartolucci.repositories.ContenidoRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class ContenidoService {

    @Autowired
    ContenidoRepository contenidoRepository;

    @Autowired
    ActividadRepository actividadRepository;

    public ArrayList<Contenido> GetAllContenidos() {
        return (ArrayList<Contenido>) contenidoRepository.findAll();
    }

    public Optional<Contenido> GetContenidoById(long id) {
        return contenidoRepository.findById(id);
    }

    public ArrayList<Contenido> GetContenidoByIdActividad(int idActividad) {
        Optional<Actividad> actividad
                = actividadRepository.findById(idActividad);
        if (actividad.isPresent()) {
            return contenidoRepository.findByActividad(actividad.get());
        } else {
            return new ArrayList<Contenido>();
        }
    }

    public ArrayList<Contenido> GetContenidoByStatus(boolean activo) {
        return (ArrayList<Contenido>) contenidoRepository.findByActivo(activo);
    }

    public Contenido SaveContenido(ContenidoSaveDto contenidoSaveDto) {
        Optional<Actividad> actividad
                = actividadRepository.findById(
                        contenidoSaveDto.getIdActividad());
        if (actividad.isPresent()) {
            Contenido contenido = new Contenido(0,
                    actividad.get(),
                    contenidoSaveDto.getDescripcion(),
                    contenidoSaveDto.isIsEnunciado(),
                    contenidoSaveDto.isIsRespuesta(), true, null);
            return contenidoRepository.save(contenido);
        } else {
            return null;
        }
    }

    public Contenido UpdateContenido(ContenidoUpdateDto contenidoUpdateDto) {
        Optional<Contenido> contenido
                = contenidoRepository.findById(contenidoUpdateDto.getId());
        if (contenido.isPresent()) {
            Optional<Actividad> actividad = actividadRepository.findById(
                    contenidoUpdateDto.getIdActividad());
            if (actividad.isPresent()) {
                contenido.get().setActividad(actividad.get());
                contenido.get().setDescripcion(
                        contenidoUpdateDto.getDescripcion());
                contenido.get().setEnunciado(contenidoUpdateDto.isIsEnunciado());
                contenido.get().setRespuesta(contenidoUpdateDto.isIsRespuesta());
                contenido.get().setActivo(contenidoUpdateDto.isActivo());
                return contenidoRepository.save(contenido.get());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
