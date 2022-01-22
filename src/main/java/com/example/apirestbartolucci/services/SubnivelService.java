/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.subnivel.SubnivelSaveDto;
import com.example.apirestbartolucci.dtos.subnivel.SubnivelUpdateDto;
import com.example.apirestbartolucci.models.Nivel;
import com.example.apirestbartolucci.models.Subnivel;
import com.example.apirestbartolucci.repositories.NivelRepository;
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
public class SubnivelService {

    @Autowired
    SubnivelRepository subnivelRepository;

    @Autowired
    NivelRepository nivelRepository;

    public ArrayList<Subnivel> GetAllSubniveles() {
        return (ArrayList<Subnivel>) subnivelRepository.findAll();
    }

    public Optional<Subnivel> GetSubnivelById(int id) {
        return subnivelRepository.findById(id);
    }

    public Optional<Subnivel> GetSubnivelByNombre(String nombre) {
        return subnivelRepository.findByNombre(nombre);
    }

    public ArrayList<Subnivel> GetSubnivelByStatus(boolean activo) {
        return (ArrayList<Subnivel>) subnivelRepository.findByActivo(activo);
    }

    public ArrayList<Subnivel> GetSubnivelByIdNivel(int idNivel) {
        Optional<Nivel> nivel = nivelRepository.findById(idNivel);
        if (nivel.isPresent()) {
            return (ArrayList<Subnivel>) subnivelRepository
                    .findByNivel(nivel.get());
        } else {
            return new ArrayList<Subnivel>();
        }
    }

    public Subnivel SaveSubnivel(SubnivelSaveDto subnivelSaveDto) {
        if (subnivelSaveDto.getMultimedia().getPublicid() == null
                || subnivelSaveDto.getMultimedia().getUrl() == null) {
            return null;
        } else {
            Optional<Nivel> nivel
                    = nivelRepository.findById(subnivelSaveDto.getIdNivel());
            if (nivel.isPresent()) {
                if (subnivelRepository.findByNombre(
                        subnivelSaveDto.getNombre()).isPresent()) {
                    return null;
                } else {
                    int count = (int) subnivelRepository.findByNivel(
                            nivel.get()).size();
                    Subnivel subnivel = new Subnivel(0,
                            nivel.get(),
                            subnivelSaveDto.getNombre(),
                            subnivelSaveDto.getDescripcion(),
                            0, count + 1,
                            subnivelSaveDto.getMultimedia().getPublicid(),
                            subnivelSaveDto.getMultimedia().getUrl(), true);
                    return subnivelRepository.save(subnivel);
                }
            } else {
                return null;
            }
        }
    }

    public Subnivel UpdateSubnivel(SubnivelUpdateDto subnivelUpdateDto) {
        Optional<Subnivel> subnivel
                = subnivelRepository.findById(subnivelUpdateDto.getId());
        if (subnivel.isPresent()) {
            subnivel.get().setNombre(subnivelUpdateDto.getNombre());
            subnivel.get().setDescripcion(subnivelUpdateDto.getDescripcion());
            subnivel.get().setActivo(subnivelUpdateDto.isActivo());
            return subnivelRepository.save(subnivel.get());
        } else {
            return null;
        }
    }

}
