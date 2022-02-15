/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.models.Inventario;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.InventarioService;
import java.util.ArrayList;
import java.util.Optional;
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
        ArrayList<Inventario> inventarios
                = inventarioService.GetAllInventarios();
        if (inventarios.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(inventarios, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") long id) {
        Optional<Inventario> inventario = inventarioService.GetInventrioById(id);
        if (inventario.isPresent()) {
            return new ResponseEntity(inventario, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe inventario con "
                    + "id: " + String.valueOf(id)), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byEstudiante")
    public ResponseEntity<?> GetByIdEstudiante(
            @RequestParam("idEstudiante") int id) {
        ArrayList<Inventario> inventarios
                = inventarioService.GetInventarioByIdEstudiante(id);
        if (inventarios.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay inventario para el "
                    + "estudiante con id: " + String.valueOf(id)), HttpStatus.OK);
        } else {
            return new ResponseEntity(inventarios, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/bySelected")
    public ResponseEntity<?> GetBySeleccionado(
            @RequestParam("idEstudiante") int id,
            @RequestParam("selected") boolean seleccionado) {
        ArrayList<Inventario> inventarios
                = inventarioService.GetInventarioByIdEstudianteAndSelect(
                        id, seleccionado);
        if (inventarios.isEmpty()) {
            return new ResponseEntity(new Mensaje("El estudiante con id: "
                    + String.valueOf(id) + " no ha seleccionado ningun articulo "
                    + "para su avatar"), HttpStatus.OK);
        } else {
            return new ResponseEntity(inventarios, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus")
    public ResponseEntity<?> GetByStatus(
            @RequestParam("idEstudiante") int id,
            @RequestParam("activo") boolean activo) {
        ArrayList<Inventario> inventarios
                = inventarioService.GetInventarioByIdEstudianteAndActivo(
                        id, activo);
        if (inventarios.isEmpty()) {
            return new ResponseEntity(new Mensaje("El estudiante con id: "
                    + String.valueOf(id) + " no tiene articulos con status: "
                    + String.valueOf(activo)), HttpStatus.OK);
        } else {
            return new ResponseEntity(inventarios, HttpStatus.OK);
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
        Inventario inventario
                = inventarioService.SaveInventario(idEstudiante, idArticulo);
        if (inventario == null) {
            return new ResponseEntity(new Mensaje("Imposible adquirir articulo, "
                    + "posibles casusas:\nId estudiante inexistente\n"
                    + "Id articulo inexistente\nCaritas insuficientes"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(inventario, HttpStatus.OK);
        }
    }

}
