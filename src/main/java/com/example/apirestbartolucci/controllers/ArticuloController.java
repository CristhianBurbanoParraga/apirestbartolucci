/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.articulo.ArticuloSaveDto;
import com.example.apirestbartolucci.dtos.articulo.ArticuloUpdateDto;
import com.example.apirestbartolucci.models.Articulo;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.ArticuloService;
import io.swagger.annotations.ApiOperation;
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
 * @author PC
 */
@RestController
@RequestMapping(path = "/articulo", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @ApiOperation(value = "Descripcion de la lista")
    @GetMapping()
    public ResponseEntity<?> GetAll() {
        ArrayList<Articulo> articulos = articuloService.GetAllArticulos();
        if (articulos.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(articulos, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        Optional<Articulo> articulo = articuloService.GetArticuloById(id);
        if (articulo.isPresent()) {
            return new ResponseEntity(articulo, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe articulo con id: "
                    + String.valueOf(id)), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byNombre")
    public ResponseEntity<?> GetByNombre(
            @RequestParam("nombre") String nombre) {
        Optional<Articulo> articulo
                = articuloService.GetArticuloByNombre(nombre);
        if (articulo.isPresent()) {
            return new ResponseEntity(articulo, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe articulo con "
                    + "nombre: " + nombre), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus")
    public ResponseEntity<?> GetAllByStatus(
            @RequestParam("status") boolean activo) {
        ArrayList<Articulo> articulos
                = articuloService.GetArticuloByStatus(activo);
        if (articulos.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con Activo: "
                    + activo), HttpStatus.OK);
        } else {
            return new ResponseEntity(articulos, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(@RequestBody ArticuloSaveDto articuloSaveDto) {
        Articulo articulo = articuloService.SaveArticulo(articuloSaveDto);
        if (articulo != null) {
            return new ResponseEntity(articulo, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Error al guardar, posibles "
                    + "causas: \nEl campo 'nombre' ya existe ó\nCampos del "
                    + "archivo multimedia erroneos"), HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody ArticuloUpdateDto articuloUpdateDto) {
        Articulo articulo = articuloService.UpdateArticulo(articuloUpdateDto);
        if (articulo != null) {
            return new ResponseEntity(articulo, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Articulo con id: "
                    + articuloUpdateDto.getId() + " inexistente"),
                    HttpStatus.OK);
        }
    }

}
