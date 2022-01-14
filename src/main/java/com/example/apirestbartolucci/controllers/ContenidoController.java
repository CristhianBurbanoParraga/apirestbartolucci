/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Contenido;
import com.example.apirestbartolucci.services.ContenidoService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ApiOperation(value = "GetAllContenidos",
            notes = "Obtiene un array json de todos contenidos de actividades registtados")
    @GetMapping()
    public ArrayList<Contenido> GetAllContenidos() {
        return contenidoService.GetAllContenidos();
    }

    @ApiOperation(value = "GetContenidoById",
            notes = "Obtiene un json de un contenido de actividad por id pasado por parametro")
    @GetMapping(path = "/{id}")
    public Optional<Contenido> GetContenidoById(@PathVariable("id") int id) {
        return contenidoService.GetContenidoById(id);
    }

    @ApiOperation(value = "SaveContenido",
            notes = "Registra un contenido de actividad y devuelve el objeto registrado en json")
    @PostMapping()
    public Contenido SaveContenido(@RequestBody Contenido contenido) {
        return contenidoService.SaveAndUpdateContenido(contenido);
    }

    @ApiOperation(value = "UpdateContenido",
            notes = "Actualiza un contenido de actividad y devuelve el objeto modificado en json")
    @PutMapping
    public Contenido UpdateContenido(@RequestBody Contenido contenido) {
        return contenidoService.SaveAndUpdateContenido(contenido);
    }

    @ApiOperation(value = "DeleteContenido",
            notes = "Elimina un contenido de actividad por id pasado por parametro y devuelve un booleano")
    @DeleteMapping(path = "/{id}")
    public boolean DeleteContenido(@PathVariable("id") int id) {
        return contenidoService.DeleteContenidoById(id);
    }

    @ApiOperation(value = "GetContenidoByActividad",
            notes = "Obtiene un array json de los contenidos de actividades registrados por actividad")
    @PostMapping(path = "/byactividad")
    public ArrayList<Contenido> GetContenidoByActividad(@RequestBody Actividad actividad) {
        return contenidoService.GetContenidoByActividad(actividad);
    }

    @ApiOperation(value = "GetContenidoByTipo",
            notes = "Obtiene un array json de los contenidos de actividades registrados por tipo")
    @PostMapping(path = "/bytype")
    public ArrayList<Contenido> GetContenidoByTipo(@PathVariable String tipo) {
        return contenidoService.GetContenidoByTipo(tipo);
    }

}
