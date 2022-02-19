/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.dtos.nivel.NivelMessageDto;
import com.example.apirestbartolucci.dtos.nivel.NivelSaveDto;
import com.example.apirestbartolucci.dtos.nivel.NivelUpdateDto;
import com.example.apirestbartolucci.models.Nivel;
import com.example.apirestbartolucci.repositories.NivelRepository;
import com.example.apirestbartolucci.repositories.SubnivelRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NivelService {

    @Autowired
    NivelRepository nivelRepository;

    @Autowired
    SubnivelRepository subnivelRepository;

    public NivelMessageDto GetAllNiveles() {
        ArrayList<Nivel> niveles
                = (ArrayList<Nivel>) nivelRepository.findByOrderByPrioridadAsc();
        if (niveles.isEmpty()) {
            return new NivelMessageDto(false, "No hay registros", null, null);
        } else {
            return new NivelMessageDto(true, "Ok", null, niveles);
        }
    }

    public NivelMessageDto GetNivelById(int id) {
        Optional<Nivel> nivel = nivelRepository.findById(id);
        if (nivel.isPresent()) {
            return new NivelMessageDto(true, "Ok", nivel.get(), null);
        } else {
            return new NivelMessageDto(false, "Id de nivel inexistente", null, null);
        }
    }

    public NivelMessageDto GetNivelByNombre(String nombre) {
        Optional<Nivel> nivel = nivelRepository.findByNombre(nombre);
        if (nivel.isPresent()) {
            return new NivelMessageDto(true, "Ok", nivel.get(), null);
        } else {
            return new NivelMessageDto(false, "No hay nivel con Nombre: "
                    + nombre, null, null);
        }
    }

    public NivelMessageDto GetNivelByStatus(boolean activo) {
        ArrayList<Nivel> niveles
                = (ArrayList<Nivel>) nivelRepository.findByActivo(activo);
        if (niveles.isEmpty()) {
            return new NivelMessageDto(false, "No hay niveles con Estado: "
                    + activo, null, null);
        } else {
            return new NivelMessageDto(true, "Ok", null, niveles);
        }
    }

    public NivelMessageDto SaveNivel(NivelSaveDto nivelDto) {
        if (nivelDto.getMultimedia().getPublicid() == null
                || nivelDto.getMultimedia().getUrl() == null) {
            return new NivelMessageDto(false, "Los campos PublicId y Url no"
                    + " pueden ser nulos", null, null);
        } else {
            Optional<Nivel> niveloptional
                    = nivelRepository.findByNombre(nivelDto.getNombre());
            if (niveloptional.isPresent()) {
                return new NivelMessageDto(false, "Ya existe un nivel con Nombre: "
                        + nivelDto.getNombre(), null, null);
            } else {
                int count = (int) nivelRepository.count();
                Nivel nivel = new Nivel(0, nivelDto.getNombre(),
                        nivelDto.getDescripcion(),
                        count + 1,
                        nivelDto.getMultimedia().getPublicid(),
                        nivelDto.getMultimedia().getUrl(),
                        true, null);
                return new NivelMessageDto(true, "Ok",
                        nivelRepository.save(nivel), null);
            }
        }
    }

    public NivelMessageDto UpdateNivel(NivelUpdateDto nivelDto) {
        if (nivelDto.getMultimedia().getPublicid() == null
                || nivelDto.getMultimedia().getUrl() == null) {
            return new NivelMessageDto(false, "Los campos PublicId y Url no"
                    + " pueden ser nulos", null, null);
        } else {
            Optional<Nivel> nivel = nivelRepository.findById(nivelDto.getId());
            if (nivel.isPresent()) {
                nivel.get().setNombre(nivelDto.getNombre());
                nivel.get().setDescripcion(nivelDto.getDescripcion());
                nivel.get().setActivo(nivelDto.isActivo());
                nivel.get().setPublicid(nivelDto.getMultimedia().getPublicid());
                nivel.get().setUrl(nivelDto.getMultimedia().getUrl());
                return new NivelMessageDto(true, "Ok",
                        nivelRepository.save(nivel.get()), null);
            } else {
                return new NivelMessageDto(false, "Id de nivel inexistente", null, null);
            }
        }
    }

}
