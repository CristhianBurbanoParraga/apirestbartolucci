/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.historial.HistorialSaveDto;
import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Contenido;
import com.example.apirestbartolucci.models.Estudiante;
import com.example.apirestbartolucci.models.Historial;
import com.example.apirestbartolucci.repositories.ActividadRepository;
import com.example.apirestbartolucci.repositories.ContenidoRepository;
import com.example.apirestbartolucci.repositories.EstudianteRepository;
import com.example.apirestbartolucci.repositories.HistorialRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
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

    @Autowired
    ContenidoRepository contenidoRepository;

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

    public Historial SaveHistorial(HistorialSaveDto historialSaveDto) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(
                historialSaveDto.getIdEstudiante());
        if (estudiante.isPresent()) {
            Optional<Actividad> actividad = actividadRepository.findById(
                    historialSaveDto.getIdActividad());
            if (actividad.isPresent()) {
                if (!historialSaveDto.isStatusRespuesta()
                        && historialSaveDto.getIdsContenido().isEmpty()) {
                    return null;
                } else {
                    Historial historial = new Historial(0,
                            actividad.get(),
                            estudiante.get(),
                            new Date());
                    estudiante.get().setStockcaritas(
                            estudiante.get().getStockcaritas()
                            + actividad.get().getRecompensavalor());
                    if (historialSaveDto.isStatusRespuesta()
                            && historialSaveDto.getIdsContenido().isEmpty()) {
                        estudianteRepository.save(estudiante.get());
                        historialRepository.save(historial);
                        return historial;
                    } else {
                        int count = historialSaveDto.getIdsContenido().size();
                        int countAux = 0;
                        ArrayList<Contenido> contenidos
                                = contenidoRepository.findByActividad(actividad.get());
                        for (Map.Entry<String, Long> entry
                                : historialSaveDto.getIdsContenido().entrySet()) {
                            for (int j = 0; j < contenidos.size(); j++) {
                                if (contenidos.get(j).getId() == entry.getValue()
                                        && contenidos.get(j).isRespuesta()) {
                                    countAux++;
                                    break;
                                }
                            }
                        }
                        if (count == countAux) {
                            estudianteRepository.save(estudiante.get());
                            historialRepository.save(historial);
                            return historial;
                        } else {
                            return null;
                        }
                    }
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
