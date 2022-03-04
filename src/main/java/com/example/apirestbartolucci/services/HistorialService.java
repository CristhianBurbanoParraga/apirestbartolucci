/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.historial.HistorialDto;
import com.example.apirestbartolucci.dtos.historial.HistorialListActividadesDto;
import com.example.apirestbartolucci.dtos.historial.HistorialListDto;
import com.example.apirestbartolucci.dtos.historial.HistorialMessageDto;
import com.example.apirestbartolucci.dtos.historial.HistorialSaveDto;
import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Contenido;
import com.example.apirestbartolucci.models.Docente;
import com.example.apirestbartolucci.models.Estudiante;
import com.example.apirestbartolucci.models.Grupo;
import com.example.apirestbartolucci.models.Historial;
import com.example.apirestbartolucci.repositories.ActividadRepository;
import com.example.apirestbartolucci.repositories.ContenidoRepository;
import com.example.apirestbartolucci.repositories.DocenteRepository;
import com.example.apirestbartolucci.repositories.EstudianteRepository;
import com.example.apirestbartolucci.repositories.GrupoRepository;
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

    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    GrupoRepository grupoRepository;

    public HistorialMessageDto GetAllHistorial() {
        ArrayList<Historial> historiales
                = (ArrayList<Historial>) historialRepository.findAll();
        if (historiales.isEmpty()) {
            return new HistorialMessageDto(false, "No hay registros",
                    null, null, null, null);
        } else {
            ArrayList<Integer> ids = new ArrayList<>();
            ArrayList<HistorialListDto> list = new ArrayList<>();
            for (int i = 0; i < historiales.size(); i++) {
                if (!ids.contains(historiales.get(i).getEstudiante().getId())) {
                    ids.add(historiales.get(i).getEstudiante().getId());
                    HistorialListDto listItem = GetHistorialByIdEstudiante(
                            historiales.get(i).getEstudiante().getId()).getListDto();
                    list.add(listItem);
                }
            }
            return new HistorialMessageDto(true, "Ok",
                    null, null, null, list);
        }
    }

    public HistorialMessageDto GetHistorialById(long id) {
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
            return new HistorialMessageDto(true, "Ok",
                    null, dto, null, null);
        } else {
            return new HistorialMessageDto(false, "No existe historial con Id: "
                    + id, null, null, null, null);
        }
    }

    public HistorialMessageDto GetHistorialByIdEstudiante(int idEstudiante) {
        Optional<Estudiante> estudiante
                = estudianteRepository.findById(idEstudiante);
        if (estudiante.isPresent()) {
            ArrayList<Historial> historiales
                    = historialRepository.findByEstudiante(estudiante.get());
            if (historiales.isEmpty()) {
                return new HistorialMessageDto(false, "No hay registros de "
                        + "historial para el Estudiante con Id: " + idEstudiante,
                        null, null, null, null);
            } else {
                ArrayList<HistorialListActividadesDto> listActividades
                        = new ArrayList<>();
                for (int i = 0; i < historiales.size(); i++) {
                    HistorialListActividadesDto item = new HistorialListActividadesDto(
                            historiales.get(i).getId(),
                            historiales.get(i).getActividad().getId(),
                            historiales.get(i).getActividad().getNombre(),
                            historiales.get(i).getActividad().getDescripcion(),
                            historiales.get(i).getFecha().toString(),
                            historiales.get(i).getRecompensaganada());
                    listActividades.add(item);
                }
                HistorialListDto list = new HistorialListDto(
                        estudiante.get().getId(),
                        estudiante.get().getNombres() + " "
                        + estudiante.get().getApellidos(),
                        listActividades);
                return new HistorialMessageDto(true, "Ok", null, null, list, null);
            }
        } else {
            return new HistorialMessageDto(false, "No existe un Estudiante con Id: "
                    + idEstudiante, null, null, null, null);
        }
    }

    public HistorialMessageDto GetHistorialByIdDocente(int idDocente) {
        Optional<Docente> docente = docenteRepository.findById(idDocente);
        if (docente.isPresent()) {
            ArrayList<Grupo> grupos = grupoRepository.findByDocente(docente.get());
            if (grupos.isEmpty()) {
                return new HistorialMessageDto(false, "El docente no tiene estudiantes asignados",
                        null, null, null, null);
            } else {
                ArrayList<Integer> ids = new ArrayList<>();
                ArrayList<HistorialListDto> list = new ArrayList<>();
                for (int i = 0; i < grupos.size(); i++) {
                    if (!ids.contains(grupos.get(i).getEstudiante().getId())) {
                        ids.add(grupos.get(i).getEstudiante().getId());
                        HistorialListDto listItem = GetHistorialByIdEstudiante(
                                grupos.get(i).getEstudiante().getId()).getListDto();
                        if (listItem != null) {
                            list.add(listItem);
                        }
                    }
                }
                if (list.isEmpty()) {
                    return new HistorialMessageDto(false, "Los estudiantes de este docente no han hecho actividades",
                            null, null, null, new ArrayList<>());
                } else {
                    return new HistorialMessageDto(true, "Ok",
                            null, null, null, list);
                }

            }
        } else {
            return new HistorialMessageDto(false, "Id docente inexistente",
                    null, null, null, null);
        }
    }

    public HistorialMessageDto SaveHistorial(HistorialSaveDto historialSaveDto) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(
                historialSaveDto.getIdEstudiante());
        if (estudiante.isPresent()) {
            Optional<Actividad> actividad = actividadRepository.findById(
                    historialSaveDto.getIdActividad());
            if (actividad.isPresent()) {
                if (historialRepository.findByActividadAndEstudiante(
                        actividad.get(), estudiante.get()).isPresent()) {
                    return new HistorialMessageDto(false, "Esta Actividad ya fue realizada",
                            null, null, null, null);
                } else {
                    if (!historialSaveDto.isStatusRespuesta()
                            && historialSaveDto.getIdsContenido().isEmpty()) {
                        return new HistorialMessageDto(false, "Los campos de respuesta "
                                + "estan vacios y falsos, debe especificar almenos uno",
                                null, null, null, null);
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
                            return new HistorialMessageDto(true, "Ok",
                                    historialRepository.save(historial), null, null, null);
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
                                return new HistorialMessageDto(true, "Ok",
                                        historialRepository.save(historial), null,
                                        null, null);
                            } else {
                                return new HistorialMessageDto(false, "Las respuestas "
                                        + "no son correctas", null, null, null, null);
                            }
                        }
                    }
                }
            } else {
                return new HistorialMessageDto(false, "No existe una Actividad con Id: "
                        + historialSaveDto.getIdActividad(), null, null, null, null);
            }
        } else {
            return new HistorialMessageDto(false, "No existe un Estudiante con Id: "
                    + historialSaveDto.getIdEstudiante(), null, null, null, null);
        }
    }
}
