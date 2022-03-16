/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.actividad.ActividadMessageDto;
import com.example.apirestbartolucci.dtos.actividad.ActividadSaveDto;
import com.example.apirestbartolucci.dtos.actividad.ActividadUpdateDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.ActividadService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author criss
 */
@RestController
@RequestMapping(path = "/actividad", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActividadController {

    @Autowired
    ActividadService actividadService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        ActividadMessageDto actividades = actividadService.GetAllActividades();
        if (actividades.isStatus()) {
            return new ResponseEntity(actividades.getActividadesDtos(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(actividades.getActividadesDtos(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        ActividadMessageDto actividad = actividadService.GetActividadById(id);
        if (actividad.isStatus()) {
            return new ResponseEntity(actividad.getActividad(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(actividad.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus")
    public ResponseEntity<?> GetAllByStatus(
            @RequestParam("status") boolean activo) {
        ActividadMessageDto actividades
                = actividadService.GetActividadByStatus(activo);
        if (actividades.isStatus()) {
            return new ResponseEntity(actividades.getActividades(), HttpStatus.OK);
        } else {
            return new ResponseEntity(actividades.getActividades(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byNombre")
    public ResponseEntity<?> GetByNombre(
            @RequestParam("nombre") String nombre) {
        ActividadMessageDto actividad
                = actividadService.GetActividadByNombre(nombre);
        if (actividad.isStatus()) {
            return new ResponseEntity(actividad.getActividades(), HttpStatus.OK);
        } else {
            return new ResponseEntity(actividad.getActividades(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/bySubnivel")
    public ResponseEntity<?> GetBySubnivel(
            @RequestParam("idSubnivel") int idSubnivel) {
        ActividadMessageDto actividades
                = actividadService.GetActividadByIdSubnivel(idSubnivel);
        if (actividades.isStatus()) {
            return new ResponseEntity(actividades.getActividades(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(actividades.getActividades(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byDocente")
    public ResponseEntity<?> GetByDocente(
            @RequestParam("idDocente") int idDocente) {
        ActividadMessageDto actividades
                = actividadService.GetActividadByIdDocente(idDocente);
        if (actividades.isStatus()) {
            return new ResponseEntity(actividades.getActividades(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(actividades.getActividades(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/bySubnivelAndDocente")
    public ResponseEntity<?> GetBySubnivelAndDocente(
            @RequestParam("idSubnivel") int idSubnivel,
            @RequestParam("idDocente") int idDocente) {
        ActividadMessageDto actividades
                = actividadService.GetActividadBySubnivelAndDocente(idSubnivel,
                        idDocente);
        if (actividades.isStatus()) {
            return new ResponseEntity(actividades.getActividadesOtherDtos(), HttpStatus.OK);
        } else {
            return new ResponseEntity(actividades.getActividadesOtherDtos(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byTypeEvaluativa")
    public ResponseEntity<?> GetByDocenteAndTypeEval(
            @RequestParam("idDocente") int idDocente) {
        ActividadMessageDto actividades
                = actividadService.GetActividadEvByNivelAndDocente(idDocente);
        if (actividades.isStatus()) {
            return new ResponseEntity(actividades.getActividadesEv(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(actividades.getActividadesEv(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/countByNivelAndDocente")
    public ResponseEntity<?> GetCountByNivelAndDocente(
            @RequestParam("idNivel") int idNivel,
            @RequestParam("idDocente") int idDocente) {
        Map<String, Integer> mapValue = new HashMap<>();
        int count = actividadService.GetCountActividadByNivelAndDocente(
                idNivel, idDocente);
        if (count < 0) {
            return new ResponseEntity(new Mensaje("Docente inexistento o sin items"),
                    HttpStatus.OK);
        } else {
            mapValue.put("count", count);
            return new ResponseEntity(mapValue,
                    HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(
            @RequestBody ActividadSaveDto actividadSaveDto) {
        ActividadMessageDto actividad
                = actividadService.SaveActividad(actividadSaveDto);
        if (actividad.isStatus()) {
            return new ResponseEntity(actividad.getActividad(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(actividad.getMessage()), HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody ActividadUpdateDto actividadUpdateDto) {
        ActividadMessageDto actividad
                = actividadService.UpdateActividad(actividadUpdateDto);
        if (actividad.isStatus()) {
            return new ResponseEntity(actividad.getActividad(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(actividad.getMessage()),
                    HttpStatus.OK);
        }
    }

}
