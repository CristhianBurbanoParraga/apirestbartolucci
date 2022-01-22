/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.dtos.nivel.NivelSaveDto;
import com.example.apirestbartolucci.dtos.nivel.NivelUpdateDto;
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

    public ArrayList<Nivel> GetAllNiveles() {
        return (ArrayList<Nivel>) nivelRepository.findAll();
    }

    public Optional<Nivel> GetNivelById(int id) {
        return nivelRepository.findById(id);
    }

    public Optional<Nivel> GetNivelByNombre(String nombre) {
        return nivelRepository.findByNombre(nombre);
    }

    public ArrayList<Nivel> GetNivelByStatus(boolean activo) {
        return (ArrayList<Nivel>) nivelRepository.findByActivo(activo);
    }

    public Nivel SaveNivel(NivelSaveDto nivelDto) {
        if (nivelDto.getMultimedia().getPublicid() == null
                && nivelDto.getMultimedia().getUrl() == null) {
            return null;
        } else {
            Optional<Nivel> niveloptional
                    = nivelRepository.findByNombre(nivelDto.getNombre());
            if (niveloptional.isPresent()) {
                return null;
            } else {
                int count = (int) nivelRepository.count();
                Nivel nivel = new Nivel(0, nivelDto.getNombre(),
                        nivelDto.getDescripcion(),
                        count + 1,
                        nivelDto.getMultimedia().getPublicid(),
                        nivelDto.getMultimedia().getUrl(),
                        true);
                return nivelRepository.save(nivel);
            }
        }
    }

    public Nivel UpdateNivel(NivelUpdateDto nivelDto) {
        Optional<Nivel> nivel = nivelRepository.findById(nivelDto.getId());
        if (nivel.isPresent()) {
            nivel.get().setNombre(nivelDto.getNombre());
            nivel.get().setDescripcion(nivelDto.getDescripcion());
            nivel.get().setActivo(nivelDto.isActivo());
            return nivelRepository.save(nivel.get());
        } else {
            return null;
        }
    }

    public String ChangeNivelStatus(int id, boolean activo) {
        Optional<Nivel> nivel = nivelRepository.findById(id);
        if (nivel.isPresent()) {
            nivel.get().setActivo(activo);
            nivelRepository.save(nivel.get());
            return "Estado del Nivel con id: " + String.valueOf(id)
                    + " cambiada a: " + String.valueOf(activo);
        } else {
            return "No existe el Nivel con id: " + String.valueOf(id);
        }
    }

}
