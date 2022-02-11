/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.historial.HistorialListDto;
import com.example.apirestbartolucci.dtos.historial.HistorialSaveDto;
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
import org.springframework.web.bind.annotation.RequestBody;
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
        ArrayList<HistorialListDto> historial = historialService.GetAllHistorial();
        if (historial.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(historial, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") long id) {
        HistorialListDto historial = historialService.GetHistorialById(id);
        if (historial != null) {
            return new ResponseEntity(historial, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe registro con id: "
                    + String.valueOf(id)), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byEstudiante")
    public ResponseEntity<?> GetByEstudiante(
            @RequestParam("idEstudiante") int idEstudiante) {
        HistorialListDto historial
                = historialService.GetHistorialByIdEstudiante(idEstudiante);
        if (historial == null) {
            return new ResponseEntity(new Mensaje("No hay registros con "
                    + "idEstudiante: " + String.valueOf(idEstudiante)),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(historial, HttpStatus.OK);
        }

    }

    @PostMapping(path = "/completeActividad")
    public ResponseEntity<?> Save(@RequestBody HistorialSaveDto historialSaveDto) {
        Historial historial
                = historialService.SaveHistorial(historialSaveDto);
        if (historial == null) {
            return new ResponseEntity(new Mensaje("Error al guardar, posibles "
                    + "causas:\nId estudiante inexistente ó\nId actividad "
                    + "inexistente\nEl # de respuestas no coinciden con la "
                    + "actividad\nStatus y respuesta son incorrectos"), HttpStatus.OK);
        } else {
            return new ResponseEntity(historial, HttpStatus.OK);
        }
    }
}
