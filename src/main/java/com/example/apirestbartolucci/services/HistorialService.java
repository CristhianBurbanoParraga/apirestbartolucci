/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.historial.HistorialDto;
import com.example.apirestbartolucci.dtos.historial.HistorialListActividadesDto;
import com.example.apirestbartolucci.dtos.historial.HistorialListDto;
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

    public ArrayList<HistorialListDto> GetAllHistorial() {
        ArrayList<Historial> historiales
                = (ArrayList<Historial>) historialRepository.findAll();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<HistorialListDto> list = new ArrayList<HistorialListDto>();
        for (int i = 0; i < historiales.size(); i++) {
            if (!ids.contains(historiales.get(i).getEstudiante().getId())) {
                ids.add(historiales.get(i).getEstudiante().getId());
                HistorialListDto listItem = GetHistorialByIdEstudiante(
                        historiales.get(i).getEstudiante().getId());
                list.add(listItem);
            }
        }
        return list;
    }

    public HistorialDto GetHistorialById(long id) {
        Optional<Historial> historial = historialRepository.findById(id);
        if (historial.isPresent()) {
            HistorialDto dto = new HistorialDto(
                    historial.get().getId(),
                    historial.get().getEstudiante().getId(),
                    historial.get().getEstudiante().getNombres() + " "
                    + historial.get().getEstudiante().getApellidos(),
                    historial.get().getActividad().getId(),
                    historial.get().getActividad().getNombre(),
                    historial.get().getRecompensaganada());
            return dto;
        } else {
            return null;
        }
    }

    public HistorialListDto GetHistorialByIdEstudiante(int idEstudiante) {
        Optional<Estudiante> estudiante
                = estudianteRepository.findById(idEstudiante);
        if (estudiante.isPresent()) {
            ArrayList<Historial> historiales
                    = historialRepository.findByEstudiante(estudiante.get());
            ArrayList<HistorialListActividadesDto> listActividades
                    = new ArrayList<HistorialListActividadesDto>();
            for (int i = 0; i < historiales.size(); i++) {
                HistorialListActividadesDto item = new HistorialListActividadesDto(
                        historiales.get(i).getId(),
                        historiales.get(i).getActividad().getId(),
                        historiales.get(i).getActividad().getNombre(),
                        historiales.get(i).getRecompensaganada());
                listActividades.add(item);
            }
            HistorialListDto list = new HistorialListDto(
                    estudiante.get().getId(),
                    estudiante.get().getNombres() + " "
                    + estudiante.get().getApellidos(),
                    listActividades);
            return list;
        } else {
            return null;
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
                            new Date(),
                            actividad.get().getRecompensavalor());
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
