/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.dtos.nivel.NivelDto;
import com.example.apirestbartolucci.dtos.nivel.NivelMessageDto;
import com.example.apirestbartolucci.dtos.nivel.NivelSaveDto;
import com.example.apirestbartolucci.dtos.nivel.NivelUpdateDto;
import com.example.apirestbartolucci.dtos.subnivel.SubnivelDto;
import com.example.apirestbartolucci.dtos.subnivel.SubnivelMessageDto;
import com.example.apirestbartolucci.models.Nivel;
import com.example.apirestbartolucci.repositories.NivelRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NivelService {

    @Autowired
    NivelRepository nivelRepository;

    @Autowired
    SubnivelService subnivelService;

    public NivelMessageDto GetAllNiveles() {
        ArrayList<Nivel> niveles
                = (ArrayList<Nivel>) nivelRepository.findByOrderByPrioridadAsc();
        if (niveles.isEmpty()) {
            return new NivelMessageDto(false, "No hay registros", null, null, null);
        } else {
            ArrayList<NivelDto> nivelesDto = new ArrayList<>();
            for (int i = 0; i < niveles.size(); i++) {
                SubnivelMessageDto subniveles
                        = subnivelService.GetSubnivelByIdNivel(niveles.get(i).getId());
                ArrayList<SubnivelDto> subnivelesDto = new ArrayList<>();
                for (int j = 0; j < subniveles.getSubniveles().size(); j++) {
                    SubnivelDto item2 = new SubnivelDto(
                            subniveles.getSubniveles().get(j).getId(),
                            subniveles.getSubniveles().get(j).getNombre(),
                            subniveles.getSubniveles().get(j).getDescripcion(),
                            subniveles.getSubniveles().get(j).getNumactividades(),
                            subniveles.getSubniveles().get(j).getPrioridad(),
                            subniveles.getSubniveles().get(j).getPublicid(),
                            subniveles.getSubniveles().get(j).getUrl(),
                            subniveles.getSubniveles().get(j).isActivo());
                    subnivelesDto.add(item2);
                }
                NivelDto item = new NivelDto(
                        niveles.get(i).getId(),
                        niveles.get(i).getNombre(),
                        niveles.get(i).getDescripcion(),
                        niveles.get(i).getPrioridad(),
                        niveles.get(i).getPublicid(),
                        niveles.get(i).getUrl(),
                        niveles.get(i).isActivo(),
                        subnivelesDto);
                nivelesDto.add(item);
            }
            return new NivelMessageDto(true, "Ok", null, null, nivelesDto);
        }
    }

    public NivelMessageDto GetNivelById(int id) {
        Optional<Nivel> nivel = nivelRepository.findById(id);
        if (nivel.isPresent()) {
            return new NivelMessageDto(true, "Ok", nivel.get(), null, null);
        } else {
            return new NivelMessageDto(false, "Id de nivel inexistente", null, null, null);
        }
    }

    public NivelMessageDto GetNivelByNombre(String nombre) {
        Optional<Nivel> nivel = nivelRepository.findByNombre(nombre);
        if (nivel.isPresent()) {
            return new NivelMessageDto(true, "Ok", nivel.get(), null, null);
        } else {
            return new NivelMessageDto(false, "No hay nivel con Nombre: "
                    + nombre, null, null, null);
        }
    }

    public NivelMessageDto GetNivelByStatus(boolean activo) {
        ArrayList<Nivel> niveles
                = (ArrayList<Nivel>) nivelRepository.findByActivo(activo);
        if (niveles.isEmpty()) {
            return new NivelMessageDto(false, "No hay niveles con Estado: "
                    + activo, null, null, null);
        } else {
            return new NivelMessageDto(true, "Ok", null, niveles, null);
        }
    }

    public NivelMessageDto SaveNivel(NivelSaveDto nivelDto) {
        if (nivelDto.getMultimedia().getPublicid() == null
                || nivelDto.getMultimedia().getUrl() == null) {
            return new NivelMessageDto(false, "Los campos PublicId y Url no"
                    + " pueden ser nulos", null, null, null);
        } else {
            Optional<Nivel> niveloptional
                    = nivelRepository.findByNombre(nivelDto.getNombre());
            if (niveloptional.isPresent()) {
                return new NivelMessageDto(false, "Ya existe un nivel con Nombre: "
                        + nivelDto.getNombre(), null, null, null);
            } else if (nivelRepository.findByPublicid(nivelDto.getMultimedia()
                    .getPublicid()).isPresent()) {
                return new NivelMessageDto(false, "Ya existe un nivel con Publicid: "
                        + nivelDto.getMultimedia().getPublicid(), null, null, null);
            } else {
                int count = (int) nivelRepository.count();
                Nivel nivel = new Nivel(0, nivelDto.getNombre(),
                        nivelDto.getDescripcion(),
                        count + 1,
                        nivelDto.getMultimedia().getPublicid(),
                        nivelDto.getMultimedia().getUrl(),
                        true, null);
                return new NivelMessageDto(true, "Ok",
                        nivelRepository.save(nivel), null, null);
            }
        }
    }

    public NivelMessageDto UpdateNivel(NivelUpdateDto nivelDto) {
        if (nivelDto.getMultimedia().getPublicid() == null
                || nivelDto.getMultimedia().getUrl() == null) {
            return new NivelMessageDto(false, "Los campos PublicId y Url no"
                    + " pueden ser nulos", null, null, null);
        } else {
            Optional<Nivel> nivel = nivelRepository.findById(nivelDto.getId());
            if (nivel.isPresent()) {
                nivel.get().setNombre(nivelDto.getNombre());
                nivel.get().setDescripcion(nivelDto.getDescripcion());
                nivel.get().setActivo(nivelDto.isActivo());
                nivel.get().setPublicid(nivelDto.getMultimedia().getPublicid());
                nivel.get().setUrl(nivelDto.getMultimedia().getUrl());
                return new NivelMessageDto(true, "Ok",
                        nivelRepository.save(nivel.get()), null, null);
            } else {
                return new NivelMessageDto(false, "Id de nivel inexistente", null, null, null);
            }
        }
    }

}
