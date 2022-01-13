/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

/**
 *
 * @author criss
 */
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

    public Nivel SaveAndUpdateNivel(Nivel nivel) {
        return nivelRepository.save(nivel);
    }

    public boolean DeleteNivelById(int id) {
        try {
            nivelRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<Nivel> GetNivelByNombre(String nombre) {
        return nivelRepository.findByNombre(nombre);
    }

}
