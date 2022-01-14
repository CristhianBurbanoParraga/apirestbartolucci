/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Subnivel;
import com.example.apirestbartolucci.repositories.ActividadRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadService {

    @Autowired
    ActividadRepository actividadRepository;

    public ArrayList<Actividad> GetAllActividades() {
        return (ArrayList<Actividad>) actividadRepository.findAll();
    }

    public Optional<Actividad> GetActividadById(int id) {
        return actividadRepository.findById(id);
    }

    public Actividad SaveAndUpdateActividad(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    public boolean DeleteActividadById(int id) {
        try {
            actividadRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Actividad> GetActividadBySubnivel(Subnivel subnivel) {
        return (ArrayList<Actividad>) actividadRepository.findBySubnivel(subnivel);
    }

    public ArrayList<Actividad> GetActividadByTipo(String tipo) {
        return (ArrayList<Actividad>) actividadRepository.findByTipo(tipo);
    }

    public ArrayList<Actividad> GetActividadByValor(int valor) {
        return (ArrayList<Actividad>) actividadRepository.findByValor(valor);
    }
}
