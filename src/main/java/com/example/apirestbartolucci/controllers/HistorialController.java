/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.models.Historial;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.HistorialService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author criss
 */
@RestController
@RequestMapping(path = "/historial")
public class HistorialController {

    @Autowired
    HistorialService historialService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        ArrayList<Historial> historial = historialService.GetAllHistorial();
        if (historial.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(historial, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") long id) {
        Optional<Historial> historial = historialService.GetHistorialById(id);
        if (historial.isPresent()) {
            return new ResponseEntity(historial, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe registro con id: "
                    + String.valueOf(id)), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byEstudiante")
    public ResponseEntity<?> GetByEstudiante(
            @RequestParam("idEstudiante") int idEstudiante) {
        ArrayList<Historial> historial
                = historialService.GetHistorialByIdEstudiante(idEstudiante);
        if (historial.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con "
                    + "idEstudiante: " + String.valueOf(idEstudiante)),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(historial, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/completeActividad")
    public ResponseEntity<?> Save(
            @RequestParam("idEstudiante") int idEstudiante,
            @RequestParam("idActividad") int idActividad) {
        Historial historial
                = historialService.SaveHistorial(idEstudiante, idActividad);
        if (historial == null) {
            return new ResponseEntity(new Mensaje("Error al guardar, posibles "
                    + "causas:\nId estudiante inexistente ó\nId actividad "
                    + "inexistente"), HttpStatus.OK);
        } else {
            return new ResponseEntity(historial, HttpStatus.OK);
        }
    }
}
