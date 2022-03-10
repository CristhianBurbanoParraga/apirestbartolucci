/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.subnivel.SubnivelDto;
import com.example.apirestbartolucci.dtos.subnivel.SubnivelMessageDto;
import com.example.apirestbartolucci.dtos.subnivel.SubnivelSaveDto;
import com.example.apirestbartolucci.dtos.subnivel.SubnivelTwoVersionDto;
import com.example.apirestbartolucci.dtos.subnivel.SubnivelUpdateDto;
import com.example.apirestbartolucci.models.Nivel;
import com.example.apirestbartolucci.models.Subnivel;
import com.example.apirestbartolucci.repositories.NivelRepository;
import com.example.apirestbartolucci.repositories.SubnivelRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class SubnivelService {

    @Autowired
    SubnivelRepository subnivelRepository;

    @Autowired
    NivelRepository nivelRepository;

    public SubnivelMessageDto GetAllSubniveles() {
        ArrayList<Subnivel> subniveles
                = (ArrayList<Subnivel>) subnivelRepository.findByOrderByNivelAsc(
                        Sort.by(Sort.Direction.ASC, "prioridad"));
        if (subniveles.isEmpty()) {
            return new SubnivelMessageDto(false, "No hay registros", null, null, null);
        } else {
            ArrayList<SubnivelTwoVersionDto> subnivelesDto = new ArrayList<>();
            for (int i = 0; i < subniveles.size(); i++) {
                SubnivelTwoVersionDto item = new SubnivelTwoVersionDto(
                        subniveles.get(i).getId(),
                        subniveles.get(i).getNivel().getId(),
                        subniveles.get(i).getNivel().getNombre(),
                        subniveles.get(i).getNombre(),
                        subniveles.get(i).getDescripcion(),
                        subniveles.get(i).getNumactividades(),
                        subniveles.get(i).getPrioridad(),
                        subniveles.get(i).getPublicid(),
                        subniveles.get(i).getUrl(),
                        subniveles.get(i).isActivo());
                subnivelesDto.add(item);
            }
            return new SubnivelMessageDto(true, "Ok", null, null, subnivelesDto);
        }
    }

    public SubnivelMessageDto GetSubnivelById(int id) {
        Optional<Subnivel> subnivel = subnivelRepository.findById(id);
        if (subnivel.isPresent()) {
            return new SubnivelMessageDto(true, "Ok", subnivel.get(), null, null);
        } else {
            return new SubnivelMessageDto(false, "Id de subnivel inexistente", null, null, null);
        }
    }

    public SubnivelMessageDto GetSubnivelByNombre(String nombre) {
        Optional<Subnivel> subnivel = subnivelRepository.findByNombre(nombre);
        if (subnivel.isPresent()) {
            return new SubnivelMessageDto(true, "Ok", subnivel.get(), null, null);
        } else {
            return new SubnivelMessageDto(false, "No existe subnivel con Nombre: "
                    + nombre, null, null, null);
        }
    }

    public SubnivelMessageDto GetSubnivelByStatus(boolean activo) {
        ArrayList<Subnivel> subniveles
                = (ArrayList<Subnivel>) subnivelRepository.findByActivo(activo);
        if (subniveles.isEmpty()) {
            return new SubnivelMessageDto(false, "No existe subniveles con Estado: "
                    + activo, null, null, null);
        } else {
            return new SubnivelMessageDto(true, "Ok", null, subniveles, null);
        }
    }

    public SubnivelMessageDto GetSubnivelByIdNivel(int idNivel) {
        Optional<Nivel> nivel = nivelRepository.findById(idNivel);
        if (nivel.isPresent()) {
            ArrayList<Subnivel> subniveles = (ArrayList<Subnivel>) subnivelRepository
                    .findByNivel(nivel.get(), Sort.by(Sort.Direction.ASC,
                            "prioridad"));
            if (subniveles.isEmpty()) {
                return new SubnivelMessageDto(false, "No hay subniveles con id de Nivel: "
                        + idNivel, null, new ArrayList<>(), null);
            } else {
                return new SubnivelMessageDto(true, "Ok", null, subniveles, null);
            }
        } else {
            return new SubnivelMessageDto(false, "Id nivel inexistente", null, new ArrayList<>(), null);
        }
    }

    public SubnivelMessageDto SaveSubnivel(SubnivelSaveDto subnivelSaveDto) {
        if (subnivelSaveDto.getMultimedia().getPublicid() == null
                || subnivelSaveDto.getMultimedia().getUrl() == null) {
            return new SubnivelMessageDto(false, "Los campos PublicId y Url no "
                    + "deben ser nulos", null, null, null);
        } else {
            Optional<Nivel> nivel
                    = nivelRepository.findById(subnivelSaveDto.getIdNivel());
            if (nivel.isPresent()) {
                if (subnivelRepository.findByNombre(
                        subnivelSaveDto.getNombre()).isPresent()) {
                    return new SubnivelMessageDto(false, "Ya existe subnivel con"
                            + " Nombre: " + subnivelSaveDto.getNombre(), null, null, null);
                } else if (subnivelRepository.findByPublicid(
                        subnivelSaveDto.getMultimedia().getPublicid()).isPresent()) {
                    return new SubnivelMessageDto(false, "Ya existe subnivel con"
                            + " publicid: " + subnivelSaveDto.getMultimedia()
                                    .getPublicid(), null, null, null);
                } else {
                    int count = (int) subnivelRepository.findByNivel(
                            nivel.get(), Sort.by(Sort.Direction.ASC,
                            "prioridad")).size();
                    Subnivel subnivel = new Subnivel(0,
                            nivel.get(),
                            subnivelSaveDto.getNombre(),
                            subnivelSaveDto.getDescripcion(),
                            0, count + 1,
                            subnivelSaveDto.getMultimedia().getPublicid(),
                            subnivelSaveDto.getMultimedia().getUrl(),
                            true, null);
                    return new SubnivelMessageDto(true, "Ok",
                            subnivelRepository.save(subnivel), null, null);
                }
            } else {
                return new SubnivelMessageDto(false, "Id de nivel inexistente", null, null, null);
            }
        }
    }

    public SubnivelMessageDto UpdateSubnivel(SubnivelUpdateDto subnivelUpdateDto) {
        if (subnivelUpdateDto.getMultimedia().getPublicid() == null
                || subnivelUpdateDto.getMultimedia().getUrl() == null) {
            return new SubnivelMessageDto(false, "Los campos PublicId y Url no "
                    + "deben ser nulos", null, null, null);
        } else {
            Optional<Subnivel> subnivel
                    = subnivelRepository.findById(subnivelUpdateDto.getId());
            if (subnivel.isPresent()) {
                subnivel.get().setNombre(subnivelUpdateDto.getNombre());
                subnivel.get().setDescripcion(subnivelUpdateDto.getDescripcion());
                subnivel.get().setActivo(subnivelUpdateDto.isActivo());
                subnivel.get().setPublicid(
                        subnivelUpdateDto.getMultimedia().getPublicid());
                subnivel.get().setUrl(subnivelUpdateDto.getMultimedia().getUrl());
                return new SubnivelMessageDto(true, "Ok",
                        subnivelRepository.save(subnivel.get()), null, null);
            } else {
                return new SubnivelMessageDto(false, "Id de subnivel inexistente",
                        null, null, null);
            }
        }
    }

}
