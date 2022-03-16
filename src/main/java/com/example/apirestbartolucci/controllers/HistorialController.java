/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.historial.HistorialMessageDto;
import com.example.apirestbartolucci.dtos.historial.HistorialSaveDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.HistorialService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(path = "/historial", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistorialController {

    @Autowired
    HistorialService historialService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        HistorialMessageDto historial = historialService.GetAllHistorial();
        if (historial.isStatus()) {
            return new ResponseEntity(historial.getListActividadesDto(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(historial.getListActividadesDto(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") long id) {
        HistorialMessageDto historial = historialService.GetHistorialById(id);
        if (historial.isStatus()) {
            return new ResponseEntity(historial.getHistorialDto(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(historial.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byEstudiante")
    public ResponseEntity<?> GetByEstudiante(
            @RequestParam("idEstudiante") int idEstudiante) {
        HistorialMessageDto historial
                = historialService.GetHistorialByIdEstudiante(idEstudiante);
        if (historial.isStatus()) {
            return new ResponseEntity(historial.getListDto(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(historial.getMessage()),
                    HttpStatus.OK);
        }

    }

    @GetMapping(path = "/byDocente")
    public ResponseEntity<?> GetByDocente(
            @RequestParam("idDocente") int idDocente) {
        HistorialMessageDto historial
                = historialService.GetHistorialByIdDocente(idDocente);
        if (historial.isStatus()) {
            return new ResponseEntity(historial.getListActividadesDto(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(historial.getListActividadesDto(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/countByNivelAndEstudiante")
    public ResponseEntity<?> GetCountByNivelAndEstudiante(
            @RequestParam("idNivel") int idNivel,
            @RequestParam("idEstudiante") int idEstudiante) {
        Map<String, Integer> valueMap = new HashMap<>();
        int count = historialService.GetCountHistorialByEstudianteAndNivel(idNivel,
                idEstudiante);
        if (count < 0) {
            return new ResponseEntity(new Mensaje("Estudiante inexistente o sin items"),
                    HttpStatus.OK);
        } else {
            valueMap.put("count", count);
            return new ResponseEntity(valueMap, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/completeActividad")
    public ResponseEntity<?> Save(@RequestBody HistorialSaveDto historialSaveDto) {
        HistorialMessageDto historial
                = historialService.SaveHistorial(historialSaveDto);
        if (historial.isStatus()) {
            return new ResponseEntity(historial.getHistorial(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(historial.getMessage()),
                    HttpStatus.OK);
        }
    }
}
