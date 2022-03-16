/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.articulo.ArticuloMessageDto;
import com.example.apirestbartolucci.dtos.articulo.ArticuloSaveDto;
import com.example.apirestbartolucci.dtos.articulo.ArticuloUpdateDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.ArticuloService;
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

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        ArticuloMessageDto articulos = articuloService.GetAllArticulos();
        if (articulos.isStatus()) {
            return new ResponseEntity(articulos.getArticulos(), HttpStatus.OK);
        } else {
            return new ResponseEntity(articulos.getArticulos(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        ArticuloMessageDto articulo = articuloService.GetArticuloById(id);
        if (articulo.isStatus()) {
            return new ResponseEntity(articulo.getArticulo(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(articulo.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byNombre")
    public ResponseEntity<?> GetByNombre(
            @RequestParam("nombre") String nombre) {
        ArticuloMessageDto articulo
                = articuloService.GetArticuloByNombre(nombre);
        if (articulo.isStatus()) {
            return new ResponseEntity(articulo.getArticulo(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(articulo.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus")
    public ResponseEntity<?> GetAllByStatus(
            @RequestParam("status") boolean activo) {
        ArticuloMessageDto articulos
                = articuloService.GetArticuloByStatus(activo);
        if (articulos.isStatus()) {
            return new ResponseEntity(articulos.getArticulos(), HttpStatus.OK);
        } else {
            return new ResponseEntity(articulos.getArticulos(),
                    HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(@RequestBody ArticuloSaveDto articuloSaveDto) {
        ArticuloMessageDto articulo = articuloService.SaveArticulo(articuloSaveDto);
        if (articulo.isStatus()) {
            return new ResponseEntity(articulo.getArticulo(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(articulo.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody ArticuloUpdateDto articuloUpdateDto) {
        ArticuloMessageDto articulo
                = articuloService.UpdateArticulo(articuloUpdateDto);
        if (articulo.isStatus()) {
            return new ResponseEntity(articulo.getArticulo(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(articulo.getMessage()),
                    HttpStatus.OK);
        }
    }

}
