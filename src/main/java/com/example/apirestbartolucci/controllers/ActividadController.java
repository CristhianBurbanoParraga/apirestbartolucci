/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.actividad.ActividadSaveDto;
import com.example.apirestbartolucci.dtos.actividad.ActividadUpdateDto;
import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.ActividadService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(path = "/actividad")
public class ActividadController {

    @Autowired
    ActividadService actividadService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        ArrayList<Actividad> actividades = actividadService.GetAllActividades();
        if (actividades.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(actividades, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        Optional<Actividad> actividad = actividadService.GetActividadById(id);
        if (actividad.isPresent()) {
            return new ResponseEntity(actividad, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe registro con id: "
                    + String.valueOf(id)), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus")
    public ResponseEntity<?> GetAllByStatus(
            @RequestParam("status") boolean activo) {
        ArrayList<Actividad> actividades
                = actividadService.GetActividadByStatus(activo);
        if (actividades.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con Activo: "
                    + activo), HttpStatus.OK);
        } else {
            return new ResponseEntity(actividades, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byNombre")
    public ResponseEntity<?> GetByNombre(
            @RequestParam("nombre") String nombre) {
        Optional<Actividad> actividad
                = actividadService.GetActividadByNombre(nombre);
        if (actividad.isPresent()) {
            return new ResponseEntity(actividad, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe registro "
                    + "con nombre: " + nombre), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/bySubnivel")
    public ResponseEntity<?> GetBySubnivel(
            @RequestParam("idSubnivel") int idSubnivel) {
        ArrayList<Actividad> actividades
                = actividadService.GetActividadByIdSubnivel(idSubnivel);
        if (actividades.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con "
                    + "idSubnivel: " + String.valueOf(idSubnivel)),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(actividades, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byDocente")
    public ResponseEntity<?> GetByDocente(
            @RequestParam("idDocente") int idDocente) {
        ArrayList<Actividad> actividades
                = actividadService.GetActividadByIdDocente(idDocente);
        if (actividades.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con idDocente: "
                    + String.valueOf(idDocente)), HttpStatus.OK);
        } else {
            return new ResponseEntity(actividades, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(
            @RequestBody ActividadSaveDto actividadSaveDto) {
        Actividad actividad = actividadService.SaveActividad(actividadSaveDto);
        if (actividad == null) {
            return new ResponseEntity(new Mensaje("Error al guardar, posibles "
                    + "causas:\nLos campos del archivo son nulos ó\nId subnivel "
                    + "inexistente ó\nId docente inexistente\nNombre de "
                    + "actividad existente para este docente"), HttpStatus.OK);
        } else {
            return new ResponseEntity(actividad, HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody ActividadUpdateDto actividadUpdateDto) {
        Actividad actividad
                = actividadService.UpdateActividad(actividadUpdateDto);
        if (actividad != null) {
            return new ResponseEntity(actividad, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Error al actualizar, posibles"
                    + " causas:\nId actividad inexistente\nId de subnivel "
                    + "inexistente"), HttpStatus.OK);
        }
    }

}
