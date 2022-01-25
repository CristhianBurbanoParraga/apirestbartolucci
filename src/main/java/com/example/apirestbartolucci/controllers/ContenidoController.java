/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.contenido.ContenidoSaveDto;
import com.example.apirestbartolucci.dtos.contenido.ContenidoUpdateDto;
import com.example.apirestbartolucci.models.Contenido;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.ContenidoService;
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
@RequestMapping(path = "/contenido")
public class ContenidoController {

    @Autowired
    ContenidoService contenidoService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        ArrayList<Contenido> contenidos = contenidoService.GetAllContenidos();
        if (contenidos.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(contenidos, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") long id) {
        Optional<Contenido> contenido = contenidoService.GetContenidoById(id);
        if (contenido.isPresent()) {
            return new ResponseEntity(contenido, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe registro con id: "
                    + String.valueOf(id)), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byActividad")
    public ResponseEntity<?> GetByActividad(
            @RequestParam("idActividad") int idActividad) {
        ArrayList<Contenido> contenidos
                = contenidoService.GetContenidoByIdActividad(idActividad);
        if (contenidos.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con "
                    + "idActividad: " + String.valueOf(idActividad)),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(contenidos, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus")
    public ResponseEntity<?> GetByStatus(
            @RequestParam("status") boolean activo) {
        ArrayList<Contenido> contenidos
                = contenidoService.GetContenidoByStatus(activo);
        if (contenidos.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con "
                    + "status: " + String.valueOf(activo)),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(contenidos, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(
            @RequestBody ContenidoSaveDto contenidoSaveDto) {
        Contenido contenido = contenidoService.SaveContenido(contenidoSaveDto);
        if (contenido == null) {
            return new ResponseEntity(
                    new Mensaje("Id actividad inexistente"), HttpStatus.OK);
        } else {
            return new ResponseEntity(contenido, HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody ContenidoUpdateDto contenidoUpdateDto) {
        Contenido contenido
                = contenidoService.UpdateContenido(contenidoUpdateDto);
        if (contenido == null) {
            return new ResponseEntity(new Mensaje("Error al actualizar, posibles"
                    + " causas:\nId inexistente o\nId actividad inexistente"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(contenido, HttpStatus.OK);
        }
    }

}
