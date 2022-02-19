/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.subnivel.SubnivelMessageDto;
import com.example.apirestbartolucci.dtos.subnivel.SubnivelSaveDto;
import com.example.apirestbartolucci.dtos.subnivel.SubnivelUpdateDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.SubnivelService;
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
        SubnivelMessageDto subniveles = subnivelService.GetAllSubniveles();
        if (subniveles.isStatus()) {
            return new ResponseEntity(subniveles.getSubniveles(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(subniveles.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        SubnivelMessageDto subnivel = subnivelService.GetSubnivelById(id);
        if (subnivel.isStatus()) {
            return new ResponseEntity(subnivel.getSubnivel(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(subnivel.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byNombre")
    public ResponseEntity<?> GetByNombre(
            @RequestParam("nombre") String nombre) {
        SubnivelMessageDto subnivel
                = subnivelService.GetSubnivelByNombre(nombre);
        if (subnivel.isStatus()) {
            return new ResponseEntity(subnivel.getSubnivel(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(subnivel.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus")
    public ResponseEntity<?> GetAllByStatus(
            @RequestParam("status") boolean activo) {
        SubnivelMessageDto subniveles
                = subnivelService.GetSubnivelByStatus(activo);
        if (subniveles.isStatus()) {
            return new ResponseEntity(subniveles.getSubniveles(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(subniveles.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byNivel")
    public ResponseEntity<?> GetByNivel(@RequestParam("idNivel") int idNivel) {
        SubnivelMessageDto subniveles
                = subnivelService.GetSubnivelByIdNivel(idNivel);
        if (subniveles.isStatus()) {
            return new ResponseEntity(subniveles.getSubniveles(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(subniveles.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(@RequestBody SubnivelSaveDto subnivelSaveDto) {
        SubnivelMessageDto subnivel = subnivelService.SaveSubnivel(subnivelSaveDto);
        if (subnivel.isStatus()) {
            return new ResponseEntity(subnivel.getSubnivel(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(subnivel.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody SubnivelUpdateDto subnivelUpdateDto) {
        SubnivelMessageDto subnivel
                = subnivelService.UpdateSubnivel(subnivelUpdateDto);
        if (subnivel.isStatus()) {
            return new ResponseEntity(subnivel.getSubnivel(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(subnivel.getMessage()),
                    HttpStatus.OK);
        }
    }
}
