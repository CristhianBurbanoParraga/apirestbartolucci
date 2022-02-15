/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.subnivel.SubnivelSaveDto;
import com.example.apirestbartolucci.dtos.subnivel.SubnivelUpdateDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.models.Subnivel;
import com.example.apirestbartolucci.services.SubnivelService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author criss
 */
@RestController
@RequestMapping(path = "/subnivel", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubnivelController {

    @Autowired
    SubnivelService subnivelService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        ArrayList<Subnivel> subniveles = subnivelService.GetAllSubniveles();
        if (subniveles.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(subniveles, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        Optional<Subnivel> subnivel = subnivelService.GetSubnivelById(id);
        if (subnivel.isPresent()) {
            return new ResponseEntity(subnivel, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe registro con id: "
                    + String.valueOf(id)), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byNombre")
    public ResponseEntity<?> GetByNombre(
            @RequestParam("nombre") String nombre) {
        Optional<Subnivel> subnivel
                = subnivelService.GetSubnivelByNombre(nombre);
        if (subnivel.isPresent()) {
            return new ResponseEntity(subnivel, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe registro "
                    + "con nombre: " + nombre), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus")
    public ResponseEntity<?> GetAllByStatus(
            @RequestParam("status") boolean activo) {
        ArrayList<Subnivel> subniveles = subnivelService.GetSubnivelByStatus(activo);
        if (subniveles.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con Activo: "
                    + activo), HttpStatus.OK);
        } else {
            return new ResponseEntity(subniveles, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byNivel")
    public ResponseEntity<?> GetByNivel(@RequestParam("idNivel") int idNivel) {
        ArrayList<Subnivel> subniveles
                = subnivelService.GetSubnivelByIdNivel(idNivel);
        if (subniveles.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con idNivel: "
                    + String.valueOf(idNivel)), HttpStatus.OK);
        } else {
            return new ResponseEntity(subniveles, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(@RequestBody SubnivelSaveDto subnivelSaveDto) {
        Subnivel subnivel = subnivelService.SaveSubnivel(subnivelSaveDto);
        if (subnivel == null) {
            return new ResponseEntity(new Mensaje("Error al guardar el archivo,"
                    + " o campo 'nombre' ya existe, o Nivel inexistente"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(subnivel, HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody SubnivelUpdateDto subnivelUpdateDto) {
        Subnivel subnivel = subnivelService.UpdateSubnivel(subnivelUpdateDto);
        if (subnivel != null) {
            return new ResponseEntity(subnivel, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Subnivel con id: "
                    + String.valueOf(subnivelUpdateDto.getId())
                    + " inexistente"), HttpStatus.OK);
        }
    }
}
