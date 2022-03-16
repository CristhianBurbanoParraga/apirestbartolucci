/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.contenido.ContenidoMessageDto;
import com.example.apirestbartolucci.dtos.contenido.ContenidoSaveDto;
import com.example.apirestbartolucci.dtos.contenido.ContenidoUpdateDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.ContenidoService;
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
@RequestMapping(path = "/contenido", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContenidoController {

    @Autowired
    ContenidoService contenidoService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        ContenidoMessageDto contenidos = contenidoService.GetAllContenidos();
        if (contenidos.isStatus()) {
            return new ResponseEntity(contenidos.getContenidos(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(contenidos.getContenidos(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") long id) {
        ContenidoMessageDto contenido = contenidoService.GetContenidoById(id);
        if (contenido.isStatus()) {
            return new ResponseEntity(contenido.getContenido(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(contenido.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byActividad")
    public ResponseEntity<?> GetByActividad(
            @RequestParam("idActividad") int idActividad) {
        ContenidoMessageDto contenidos
                = contenidoService.GetContenidoByIdActividad(idActividad);
        if (contenidos.isStatus()) {
            return new ResponseEntity(contenidos.getContenidos(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(contenidos.getContenidos(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus")
    public ResponseEntity<?> GetByStatus(
            @RequestParam("status") boolean activo) {
        ContenidoMessageDto contenidos
                = contenidoService.GetContenidoByStatus(activo);
        if (contenidos.isStatus()) {
            return new ResponseEntity(contenidos.getContenidos(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(contenidos.getContenidos(),
                    HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(
            @RequestBody ContenidoSaveDto contenidoSaveDto) {
        ContenidoMessageDto contenido
                = contenidoService.SaveContenido(contenidoSaveDto);
        if (contenido.isStatus()) {
            return new ResponseEntity(contenido.getContenido(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(contenido.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody ContenidoUpdateDto contenidoUpdateDto) {
        ContenidoMessageDto contenido
                = contenidoService.UpdateContenido(contenidoUpdateDto);
        if (contenido.isStatus()) {
            return new ResponseEntity(contenido.getContenido(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(contenido.getMessage()),
                    HttpStatus.OK);
        }
    }

}
