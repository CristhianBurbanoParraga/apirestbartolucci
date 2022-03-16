/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.grupo.GrupoMessageDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author criss
 */
@RestController
@RequestMapping(path = "/grupo", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        GrupoMessageDto grupos = grupoService.GetAllGrupos();
        if (grupos.isStatus()) {
            return new ResponseEntity(grupos.getListByDocenteDto(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(grupos.getListByDocenteDto(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        GrupoMessageDto grupo = grupoService.GetGrupoById(id);
        if (grupo.isStatus()) {
            return new ResponseEntity(grupo.getGrupoDto(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(grupo.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byDocente")
    public ResponseEntity<?> GetByIdDocente(
            @RequestParam("idDocente") int id) {
        GrupoMessageDto grupos = grupoService.GetGrupoByIdDocente(id);
        if (grupos.isStatus()) {
            return new ResponseEntity(grupos.getByDocenteDto(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(grupos.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PutMapping(path = "/changeStatus")
    public Mensaje ChangeStatusGrupo(@RequestParam("id") int id,
            @RequestParam("activo") boolean activo) {
        return new Mensaje(grupoService.ChangeStatusGrupo(id, activo));
    }

}
