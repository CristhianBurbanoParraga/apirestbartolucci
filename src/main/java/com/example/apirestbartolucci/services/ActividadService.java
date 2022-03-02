/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.actividad.ActividadDto;
import com.example.apirestbartolucci.dtos.actividad.ActividadListTypeEvaluativaDto;
import com.example.apirestbartolucci.dtos.actividad.ActividadMessageDto;
import com.example.apirestbartolucci.dtos.actividad.ActividadSaveDto;
import com.example.apirestbartolucci.dtos.actividad.ActividadUpdateDto;
import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Docente;
import com.example.apirestbartolucci.models.Subnivel;
import com.example.apirestbartolucci.repositories.ActividadRepository;
import com.example.apirestbartolucci.repositories.DocenteRepository;
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
public class ActividadService {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    SubnivelRepository subnivelRepository;

    @Autowired
    DocenteRepository docenteRepository;

    public ActividadMessageDto GetAllActividades() {
        ArrayList<Actividad> actividades
                = (ArrayList<Actividad>) actividadRepository.findByOrderByIdAsc();
        if (actividades.isEmpty()) {
            return new ActividadMessageDto(false, "No hay registros", null, null, null, null);
        } else {
            ArrayList<ActividadDto> actividadesDto = new ArrayList<>();
            for (int i = 0; i < actividades.size(); i++) {
                ActividadDto item = new ActividadDto(
                        actividades.get(i).getId(),
                        actividades.get(i).getSubnivel().getId(),
                        actividades.get(i).getSubnivel().getNombre(),
                        actividades.get(i).getDocente().getId(),
                        actividades.get(i).getDocente().getNombres() + " "
                        + actividades.get(i).getDocente().getApellidos(),
                        actividades.get(i).getNombre(),
                        actividades.get(i).getDescripcion(),
                        actividades.get(i).getRecompensavalor(),
                        actividades.get(i).getTipo(),
                        actividades.get(i).isActivo());
                actividadesDto.add(item);
            }
            return new ActividadMessageDto(true, "Ok", null, null, null, actividadesDto);
        }
    }

    public ActividadMessageDto GetActividadById(int id) {
        Optional<Actividad> actividad = actividadRepository.findById(id);
        if (actividad.isPresent()) {
            return new ActividadMessageDto(true, "Ok", actividad.get(), null, null, null);
        } else {
            return new ActividadMessageDto(false,
                    "No existe actividad con Id: " + id, null, null, null, null);
        }
    }

    public ActividadMessageDto GetActividadByIdSubnivel(int idSubnivel) {
        Optional<Subnivel> subnivel = subnivelRepository.findById(idSubnivel);
        if (subnivel.isPresent()) {
            ArrayList<Actividad> actividades
                    = actividadRepository.findBySubnivel(subnivel.get());
            if (actividades.isEmpty()) {
                return new ActividadMessageDto(false, "No existen actividades "
                        + "registradas con el Id Subnivel: " + idSubnivel,
                        null, null, null, null);
            } else {
                return new ActividadMessageDto(true, "Ok", null, null, actividades, null);
            }
        } else {
            return new ActividadMessageDto(false, "Id Subnivel inexistente",
                    null, null, null, null);
        }
    }

    public ActividadMessageDto GetActividadByIdDocente(int idDocente) {
        Optional<Docente> docente = docenteRepository.findById(idDocente);
        if (docente.isPresent()) {
            ArrayList<Actividad> actividades
                    = actividadRepository.findByDocente(docente.get());
            if (actividades.isEmpty()) {
                return new ActividadMessageDto(false, "No existen actividades "
                        + "registradas con el Id Docente: " + idDocente,
                        null, null, null, null);
            } else {
                return new ActividadMessageDto(true, "Ok", null, null, actividades, null);
            }
        } else {
            return new ActividadMessageDto(false, "Id Docente inexistente",
                    null, null, null, null);
        }
    }

    public ActividadMessageDto GetActividadBySubnivelAndDocente(
            int idSubnivel, int idDocente) {
        Optional<Subnivel> subnivel = subnivelRepository.findById(idSubnivel);
        if (subnivel.isPresent()) {
            Optional<Docente> docente = docenteRepository.findById(idDocente);
            if (docente.isPresent()) {
                ArrayList<Actividad> actividades = actividadRepository
                        .findBySubnivelAndDocente(subnivel.get(), docente.get());
                if (actividades.isEmpty()) {
                    return new ActividadMessageDto(false, "No existen actividades "
                            + "registradas con el Id Subnivel: " + idSubnivel
                            + " y Id Docente: " + idDocente,
                            null, null, null, null);
                } else {
                    ArrayList<Actividad> actividadesAux = new ArrayList<>();
                    for (int i = 0; i < actividades.size(); i++) {
                        if (actividades.get(i).isActivo()
                                && actividades.get(i).getTipo().equals("AC")) {
                            actividadesAux.add(actividades.get(i));
                        }
                    }
                    return new ActividadMessageDto(true, "Ok", null, null, actividadesAux, null);
                }
            } else {
                return new ActividadMessageDto(false, "Id Docente inexistente",
                        null, null, null, null);
            }
        } else {
            return new ActividadMessageDto(false, "Id Subnivel inexistente",
                    null, null, null, null);
        }
    }

    public ActividadMessageDto GetActividadByNombre(String nombre) {
        ArrayList<Actividad> actividades = actividadRepository.findByNombre(nombre);
        if (actividades.isEmpty()) {
            return new ActividadMessageDto(false, "No existe actividades con Nombre: "
                    + nombre, null, null, null, null);
        } else {
            return new ActividadMessageDto(true, "Ok", null, null, actividades, null);
        }
    }

    public ActividadMessageDto GetActividadByStatus(boolean activo) {
        ArrayList<Actividad> actividades = actividadRepository.findByActivo(activo);
        if (actividades.isEmpty()) {
            return new ActividadMessageDto(false, "No existe actividades con Estado: "
                    + activo, null, null, null, null);
        } else {
            return new ActividadMessageDto(true, "Ok", null, null, actividades, null);
        }
    }

    public ActividadMessageDto GetActividadEvByNivelAndDocente(int idDocente) {
        Optional<Docente> docente = docenteRepository.findById(idDocente);
        if (docente.isPresent()) {
            ArrayList<Actividad> actividades = actividadRepository
                    .findByDocenteAndTipo(docente.get(), "EV");
            if (actividades.isEmpty()) {
                return new ActividadMessageDto(false, "Este docente no ha creado "
                        + "actividades de tipo Evaluativas", null, null, null, null);
            } else {
                ArrayList<Integer> idsNivel = new ArrayList<>();
                ArrayList<ActividadListTypeEvaluativaDto> list = new ArrayList<>();
                for (int i = 0; i < actividades.size(); i++) {
                    if (actividades.get(i).isActivo()) {
                        if (idsNivel.contains(actividades.get(i).getSubnivel()
                                .getNivel().getId())) {
                            int index = -1;
                            for (int j = 0; j < list.size(); j++) {
                                if (list.get(j).getIdNivel() == actividades.get(i)
                                        .getSubnivel().getNivel().getId()) {
                                    index = j;
                                    break;
                                }
                            }
                            list.get(index).getActividades().add(actividades.get(i));
                        } else {
                            idsNivel.add(actividades.get(i).getSubnivel().getNivel().getId());
                            ArrayList<Actividad> itemDetil = new ArrayList<>();
                            itemDetil.add(actividades.get(i));
                            ActividadListTypeEvaluativaDto item
                                    = new ActividadListTypeEvaluativaDto(actividades.get(i)
                                            .getSubnivel().getNivel().getId(),
                                            actividades.get(i).getSubnivel().getNivel()
                                                    .getNombre(), itemDetil);
                            list.add(item);
                        }
                    }
                }
                return new ActividadMessageDto(true, "Ok", null, list, null, null);
            }
        } else {
            return new ActividadMessageDto(false, "Id de docente inexistente", null, null, null, null);
        }
    }

    public ActividadMessageDto SaveActividad(ActividadSaveDto actividadSaveDto) {
        Optional<Subnivel> subnivel = subnivelRepository
                .findById(actividadSaveDto.getIdSubnivel());
        if (subnivel.isPresent()) {
            Optional<Docente> docente = docenteRepository.findById(
                    actividadSaveDto.getIdDocente());
            if (docente.isPresent()) {
                int count = 0;
                if (actividadSaveDto.getTipo().equals("EV")) {
                    ArrayList<Actividad> actividades = actividadRepository
                            .findBySubnivelAndDocente(subnivel.get(), docente.get());
                    for (int i = 0; i < actividades.size(); i++) {
                        if (actividades.get(i).getTipo().equals("EV")) {
                            count++;
                        }
                    }
                }
                if (count >= 1) {
                    return new ActividadMessageDto(false,
                            "Ya existe una actividad de tipo Evaluativa en "
                            + "este Subnivel", null, null, null, null);
                } else {
                    subnivel.get().setNumactividades(
                            subnivel.get().getNumactividades() + 1);
                    Actividad actividad = new Actividad(0,
                            subnivel.get(),
                            docente.get(),
                            actividadSaveDto.getNombre(),
                            actividadSaveDto.getDescripcion(),
                            actividadSaveDto.getRecompensavalor(),
                            actividadSaveDto.getTipo(),
                            true, null, null);
                    subnivelRepository.save(subnivel.get());
                    actividadRepository.save(actividad);
                    return new ActividadMessageDto(true,
                            "Ok", actividad, null, null, null);
                }

            } else {
                return new ActividadMessageDto(false,
                        "Id de Docente inexistente", null, null, null, null);
            }
        } else {
            return new ActividadMessageDto(false,
                    "Id de Subnivel inexistente", null, null, null, null);
        }
    }

    public ActividadMessageDto UpdateActividad(ActividadUpdateDto actividadUpdateDto) {
        Optional<Actividad> actividad
                = actividadRepository.findById(actividadUpdateDto.getId());
        if (actividad.isPresent()) {
            Optional<Subnivel> subnivel = subnivelRepository
                    .findById(actividadUpdateDto.getIdSubnivel());
            if (subnivel.isPresent()) {
                actividad.get().setSubnivel(subnivel.get());
                actividad.get().setNombre(actividadUpdateDto.getNombre());
                actividad.get().setDescripcion(
                        actividadUpdateDto.getDescripcion());
                actividad.get().setRecompensavalor(
                        actividadUpdateDto.getRecompensavalor());
                actividad.get().setActivo(actividadUpdateDto.isActivo());
                return new ActividadMessageDto(true,
                        "Ok", actividadRepository.save(actividad.get()), null, null, null);
            } else {
                return new ActividadMessageDto(false,
                        "Id de Subnivel inexistente", null, null, null, null);
            }
        } else {
            return new ActividadMessageDto(false,
                    "Id de Actividad inexistente", null, null, null, null);
        }
    }
}
