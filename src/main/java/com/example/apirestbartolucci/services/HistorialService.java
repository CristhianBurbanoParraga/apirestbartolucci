/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Estudiante;
import com.example.apirestbartolucci.models.Historial;
import com.example.apirestbartolucci.repositories.ActividadRepository;
import com.example.apirestbartolucci.repositories.EstudianteRepository;
import com.example.apirestbartolucci.repositories.HistorialRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class HistorialService {

    @Autowired
    HistorialRepository historialRepository;

    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    ActividadRepository actividadRepository;

    public ArrayList<Historial> GetAllHistorial() {
        return (ArrayList<Historial>) historialRepository.findAll();
    }

    public Optional<Historial> GetHistorialById(long id) {
        return historialRepository.findById(id);
    }

    public ArrayList<Historial> GetHistorialByIdEstudiante(int idEstudiante) {
        Optional<Estudiante> estudiante
                = estudianteRepository.findById(idEstudiante);
        if (estudiante.isPresent()) {
            return historialRepository.findByEstudiante(estudiante.get());
        } else {
            return new ArrayList<Historial>();
        }
    }

    public Historial SaveHistorial(int idEstudiante, int idActividad) {
        Optional<Estudiante> estudiante
                = estudianteRepository.findById(idEstudiante);
        if (estudiante.isPresent()) {
            Optional<Actividad> actividad
                    = actividadRepository.findById(idActividad);
            if (actividad.isPresent()) {
                Historial historial = new Historial(0,
                        actividad.get(),
                        estudiante.get(),
                        new Date());
                return historialRepository.save(historial);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
