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
import com.example.apirestbartolucci.models.Subnivel;
import com.example.apirestbartolucci.repositories.SubnivelRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubnivelService {

    @Autowired
    SubnivelRepository subnivelRepository;

    public ArrayList<Subnivel> GetAllSubniveles() {
        return (ArrayList<Subnivel>) subnivelRepository.findAll();
    }

    public Optional<Subnivel> GetSubnivelById(int id) {
        return subnivelRepository.findById(id);
    }

    public Subnivel SaveAndUpdateSubnivel(Subnivel subnivel) {
        return subnivelRepository.save(subnivel);
    }

    public boolean DeleteSubnivelById(int id) {
        try {
            subnivelRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Subnivel> GetSubnivelByNivel(Nivel nivel) {
        return (ArrayList<Subnivel>) subnivelRepository.findSubnivelByNivel(nivel);
    }

    public Optional<Subnivel> GetSubnivelByNombre(String nombre) {
        return subnivelRepository.findSubnivelByNombre(nombre);
    }
}
