/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.models.Nivel;
import com.example.apirestbartolucci.services.NivelService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/nivel")
public class NivelController {

    @Autowired
    NivelService nivelService;

    @ApiOperation(value = "GetAllNiveles",
            notes = "Obtiene un array json de los niveles registrados")
    @GetMapping()
    public ArrayList<Nivel> GetAllNiveles() {
        return nivelService.GetAllNiveles();
    }

    @ApiOperation(value = "GetNivelById",
            notes = "Obtiene un json del nivel por id pasado por parametro")
    @GetMapping(path = "/{id}")
    public Optional<Nivel> GetNivelById(@PathVariable("id") int id) {
        return nivelService.GetNivelById(id);
    }

    @ApiOperation(value = "SaveNivel",
            notes = "Registra un nivel y devuelve el objeto registrado en json")
    @PostMapping()
    public Nivel SaveNivel(@RequestBody Nivel nivel) {
        return nivelService.SaveAndUpdateNivel(nivel);
    }

    @ApiOperation(value = "UpdateNivel",
            notes = "Actualiza un nivel y devuelve el objeto modificado en json")
    @PutMapping
    public Nivel UpdateNivel(@RequestBody Nivel nivel) {
        return nivelService.SaveAndUpdateNivel(nivel);
    }

    @ApiOperation(value = "DeleteNivel",
            notes = "Elimina un nivel por id pasado por parametro y devuelve un booleano")
    @DeleteMapping(path = "/{id}")
    public boolean DeleteNivel(@PathVariable("id") int id) {
        return nivelService.DeleteNivelById(id);
    }

    @ApiOperation(value = "GetNivelByNombre",
            notes = "Obtiene un nivel por nombre pasado por parametro y devuelve el objeto en json")
    @GetMapping(path = "/byname")
    public Optional<Nivel> GetNivelByNombre(@RequestParam("name") String nombre) {
        return nivelService.GetNivelByNombre(nombre);
    }

}
