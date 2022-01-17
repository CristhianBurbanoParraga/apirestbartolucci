/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.dtos.nivel.NivelSaveDto;
import com.example.apirestbartolucci.dtos.nivel.NivelUpdateDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.models.Nivel;
import com.example.apirestbartolucci.services.NivelService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/nivel")
public class NivelController {

    @Autowired
    NivelService nivelService;

    @GetMapping()
    public ResponseEntity<?> GetAllNiveles() {
        ArrayList<Nivel> niveles = nivelService.GetAllNiveles();
        if (niveles.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(niveles, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetNivelById(@PathVariable("id") int id) {
        Optional<Nivel> nivel = nivelService.GetNivelById(id);
        if (nivel.isPresent()) {
            return new ResponseEntity(nivel, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe registro con id: "
                    + String.valueOf(id)), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byNombre/{nombre}")
    public ResponseEntity<?> GetNivelByNombre(
            @PathVariable("nombre") String nombre) {
        Optional<Nivel> nivel = nivelService.GetNivelByNombre(nombre);
        if (nivel.isPresent()) {
            return new ResponseEntity(nivel, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe registro "
                    + "con nombre: " + nombre), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatus/{status}")
    public ResponseEntity<?> GetAllNivelesByStatus(
            @PathVariable("status") boolean activo) {
        ArrayList<Nivel> niveles = nivelService.GetNivelByStatus(activo);
        if (niveles.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con Activo: "
                    + activo), HttpStatus.OK);
        } else {
            return new ResponseEntity(niveles, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> SaveNivel(@RequestBody NivelSaveDto nivelDto) {
        Nivel nivel = nivelService.SaveNivel(nivelDto);
        if (nivel == null) {
            return new ResponseEntity(new Mensaje("Error al guardar el "
                    + "archivo o campo existente 'nombre'"), HttpStatus.OK);
        } else {
            return new ResponseEntity(nivel, HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> UpdateNivel(
            @RequestBody NivelUpdateDto nivelDto) {
        Nivel nivel = nivelService.UpdateNivel(nivelDto);
        if (nivel != null) {
            return new ResponseEntity(nivel, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Nivel con id: "
                    + String.valueOf(nivelDto.getId()) + " inexistente"),
                    HttpStatus.OK);
        }
    }

    @PutMapping(path = "/changeStatusNivel")
    public Mensaje ChangeNivelStatus(@RequestParam("id") int id,
            @RequestParam("activo") boolean activo) {
        return new Mensaje(nivelService.ChangeNivelStatus(id, activo));
    }

}
