/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.dtos.nivel.NivelMessageDto;
import com.example.apirestbartolucci.dtos.nivel.NivelSaveDto;
import com.example.apirestbartolucci.dtos.nivel.NivelUpdateDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.NivelService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/nivel", produces = MediaType.APPLICATION_JSON_VALUE)
public class NivelController {

    @Autowired
    NivelService nivelService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        NivelMessageDto niveles = nivelService.GetAllNiveles();
        if (niveles.isStatus()) {
            return new ResponseEntity(niveles.getNiveles(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(niveles.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        NivelMessageDto nivel = nivelService.GetNivelById(id);
        if (nivel.isStatus()) {
            return new ResponseEntity(nivel.getNivel(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(nivel.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byNombre/{nombre}")
    public ResponseEntity<?> GetByNombre(
            @PathVariable("nombre") String nombre) {
        NivelMessageDto nivel = nivelService.GetNivelByNombre(nombre);
        if (nivel.isStatus()) {
            return new ResponseEntity(nivel.getNivel(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(nivel.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus/{status}")
    public ResponseEntity<?> GetAllByStatus(
            @PathVariable("status") boolean activo) {
        NivelMessageDto niveles = nivelService.GetNivelByStatus(activo);
        if (niveles.isStatus()) {
            return new ResponseEntity(niveles.getNiveles(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(niveles.getMessage()), HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(@RequestBody NivelSaveDto nivelDto) {
        NivelMessageDto nivel = nivelService.SaveNivel(nivelDto);
        if (nivel.isStatus()) {
            return new ResponseEntity(nivel.getNivel(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(nivel.getMessage()), HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody NivelUpdateDto nivelDto) {
        NivelMessageDto nivel = nivelService.UpdateNivel(nivelDto);
        if (nivel.isStatus()) {
            return new ResponseEntity(nivel.getNivel(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(nivel.getMessage()),
                    HttpStatus.OK);
        }
    }

}
