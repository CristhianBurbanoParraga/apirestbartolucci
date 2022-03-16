/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.inventario.InventarioMessageDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author criss
 */
@RestController
@RequestMapping(path = "/inventario", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventarioController {

    @Autowired
    InventarioService inventarioService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        InventarioMessageDto inventarios
                = inventarioService.GetAllInventarios();
        if (inventarios.isStatus()) {
            return new ResponseEntity(inventarios.getInventarios(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(inventarios.getInventarios(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") long id) {
        InventarioMessageDto inventario = inventarioService.GetInventrioById(id);
        if (inventario.isStatus()) {
            return new ResponseEntity(inventario.getInventario(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(inventario.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byEstudiante")
    public ResponseEntity<?> GetByIdEstudiante(
            @RequestParam("idEstudiante") int id) {
        InventarioMessageDto inventarios
                = inventarioService.GetInventarioByIdEstudiante(id);
        if (inventarios.isStatus()) {
            return new ResponseEntity(inventarios.getInventarios(), HttpStatus.OK);
        } else {
            return new ResponseEntity(inventarios.getInventarios(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/bySelected")
    public ResponseEntity<?> GetBySeleccionado(
            @RequestParam("idEstudiante") int id,
            @RequestParam("selected") boolean seleccionado) {
        InventarioMessageDto inventarios
                = inventarioService.GetInventarioByIdEstudianteAndSelect(
                        id, seleccionado);
        if (inventarios.isStatus()) {
            return new ResponseEntity(inventarios.getInventarios(), HttpStatus.OK);
        } else {
            return new ResponseEntity(inventarios.getInventarios(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus")
    public ResponseEntity<?> GetByStatus(
            @RequestParam("idEstudiante") int id,
            @RequestParam("activo") boolean activo) {
        InventarioMessageDto inventarios
                = inventarioService.GetInventarioByIdEstudianteAndActivo(
                        id, activo);
        if (inventarios.isStatus()) {
            return new ResponseEntity(inventarios.getInventarios(), HttpStatus.OK);
        } else {
            return new ResponseEntity(inventarios.getInventarios(),
                    HttpStatus.OK);
        }
    }

    @PutMapping()
    public Mensaje ChangeStatusArticulo(
            @RequestParam("id") int id,
            @RequestParam("idEstudiante") int idEstudiante,
            @RequestParam("selected") boolean seleccionado,
            @RequestParam("activo") boolean activo) {
        return new Mensaje(inventarioService.ChangeStatusArticulo(
                id, idEstudiante, seleccionado, activo));
    }

    @PostMapping("/buyArticulo")
    public ResponseEntity<?> Save(
            @RequestParam("idEstudiante") int idEstudiante,
            @RequestParam("idArticulo") int idArticulo) {
        InventarioMessageDto inventario
                = inventarioService.SaveInventario(idEstudiante, idArticulo);
        if (inventario.isStatus()) {
            return new ResponseEntity(inventario.getInventario(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(inventario.getMessage()),
                    HttpStatus.OK);
        }
    }

}
