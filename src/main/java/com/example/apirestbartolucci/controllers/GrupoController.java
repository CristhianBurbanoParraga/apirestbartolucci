/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.grupo.GrupoDto;
import com.example.apirestbartolucci.dtos.grupo.GrupoListByDocenteDto;
//import com.example.apirestbartolucci.models.Grupo;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.GrupoService;
import java.util.ArrayList;
//import java.util.Date;
//import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(path = "/grupo")
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        ArrayList<GrupoListByDocenteDto> grupos = grupoService.GetAllGrupos();
        if (grupos.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(grupos, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        GrupoDto grupo = grupoService.GetGrupoById(id);
        if (grupo != null) {
            return new ResponseEntity(grupo, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe grupo con id: "
                    + String.valueOf(id)), HttpStatus.OK);
        }
    }

    /*@GetMapping(path = "/byFechaRegistro")
    public ResponseEntity<?> GetByFecharegistro(
            @RequestParam("fecha") Date fecharegistro) {
        ArrayList<Grupo> grupos
                = grupoService.GetGruposByFecharegistro(fecharegistro);
        if (grupos.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con la"
                    + " fecha: " + String.valueOf(fecharegistro)),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(grupos, HttpStatus.OK);
        }
    }*/

    @GetMapping(path = "/byDocente")
    public ResponseEntity<?> GetByIdDocente(
            @RequestParam("idDocente") int id) {
        GrupoListByDocenteDto grupos = grupoService.GetGrupoByIdDocente(id);
        if (grupos == null) {
            return new ResponseEntity(new Mensaje("No hay grupos con este "
                    + "docente"), HttpStatus.OK);
        } else {
            return new ResponseEntity(grupos, HttpStatus.OK);
        }
    }

    /*@GetMapping(path = "/byEstudiante")
    public ResponseEntity<?> GetByIdEstudiante(
            @RequestParam("idEstudiante") int id) {
        ArrayList<Grupo> grupos = grupoService.GetGrupoByIdEstudiante(id);
        if (grupos.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay grupos con este "
                    + "estudiante"), HttpStatus.OK);
        } else {
            return new ResponseEntity(grupos, HttpStatus.OK);
        }
    }*/

    @PutMapping(path = "/changeStatus"
)
    public Mensaje ChangeStatusGrupo(@RequestParam("id") int id,
            @RequestParam("activo") boolean activo) {
        return new Mensaje(grupoService.ChangeStatusGrupo(id, activo));
    }

}
