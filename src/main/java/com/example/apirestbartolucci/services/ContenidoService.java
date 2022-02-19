/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.contenido.ContenidoMessageDto;
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

    public ContenidoMessageDto GetAllContenidos() {
        ArrayList<Contenido> contenidos
                = (ArrayList<Contenido>) contenidoRepository.findAll();
        if (contenidos.isEmpty()) {
            return new ContenidoMessageDto(false, "No hay registros", null, null);
        } else {
            return new ContenidoMessageDto(true, "Ok", null, contenidos);
        }
    }

    public ContenidoMessageDto GetContenidoById(long id) {
        Optional<Contenido> contenido = contenidoRepository.findById(id);
        if (contenido.isPresent()) {
            return new ContenidoMessageDto(true, "Ok", contenido.get(), null);
        } else {
            return new ContenidoMessageDto(false, "No existe contenido con Id: "
                    + id, null, null);
        }
    }

    public ContenidoMessageDto GetContenidoByIdActividad(int idActividad) {
        Optional<Actividad> actividad
                = actividadRepository.findById(idActividad);
        if (actividad.isPresent()) {
            ArrayList<Contenido> contenidos
                    = contenidoRepository.findByActividad(actividad.get());
            if (contenidos.isEmpty()) {
                return new ContenidoMessageDto(false, "No hay contenidos "
                        + "registrados con Id de actividad: " + idActividad, null, null);
            } else {
                return new ContenidoMessageDto(true, "Ok", null, contenidos);
            }
        } else {
            return new ContenidoMessageDto(false, "No existe actividad con Id: "
                    + idActividad, null, null);
        }
    }

    public ContenidoMessageDto GetContenidoByStatus(boolean activo) {
        ArrayList<Contenido> contenidos
                = (ArrayList<Contenido>) contenidoRepository.findByActivo(activo);
        if (contenidos.isEmpty()) {
            return new ContenidoMessageDto(false, "No hay contenidos con Estado: "
                    + activo, null, null);
        } else {
            return new ContenidoMessageDto(true, "Ok", null, contenidos);
        }
    }

    public ContenidoMessageDto SaveContenido(ContenidoSaveDto contenidoSaveDto) {
        if (contenidoSaveDto.isIsEnunciado()
                && contenidoSaveDto.isIsRespuesta()) {
            return new ContenidoMessageDto(false, "Un contenido no puede ser "
                    + "enunciado y respuesta al mismo tiempo", null, null);
        } else {
            Optional<Actividad> actividad
                    = actividadRepository.findById(
                            contenidoSaveDto.getIdActividad());
            if (actividad.isPresent()) {
                Contenido contenido = new Contenido(0,
                        actividad.get(),
                        contenidoSaveDto.getDescripcion(),
                        contenidoSaveDto.isIsEnunciado(),
                        contenidoSaveDto.isIsRespuesta(), true, null);
                return new ContenidoMessageDto(true, "Ok",
                        contenidoRepository.save(contenido), null);
            } else {
                return new ContenidoMessageDto(false, "No existe actividad con Id: "
                        + contenidoSaveDto.getIdActividad(), null, null);
            }
        }
    }

    public ContenidoMessageDto UpdateContenido(ContenidoUpdateDto contenidoUpdateDto) {
        if (contenidoUpdateDto.isIsEnunciado()
                && contenidoUpdateDto.isIsRespuesta()) {
            return new ContenidoMessageDto(false, "Un contenido no puede ser "
                    + "enunciado y respuesta al mismo tiempo", null, null);
        } else {
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
                    return new ContenidoMessageDto(true, "Ok",
                            contenidoRepository.save(contenido.get()), null);
                } else {
                    return new ContenidoMessageDto(false, "No existe actividad "
                            + "con Id: " + contenidoUpdateDto.getIdActividad(), null, null);
                }
            } else {
                return new ContenidoMessageDto(false, "No existe contenido con Id: "
                        + contenidoUpdateDto.getId(), null, null);
            }
        }
    }

}
