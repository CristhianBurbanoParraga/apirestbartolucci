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
import com.example.apirestbartolucci.models.Subnivel;
import com.example.apirestbartolucci.services.SubnivelService;
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
@RequestMapping(path = "/subnivel")
public class SubnivelController {

    @Autowired
    SubnivelService subnivelService;

    @ApiOperation(value = "GetAllSubniveles")
    @GetMapping()
    public ArrayList<Subnivel> GetAllSubniveles() {
        return subnivelService.GetAllSubniveles();
    }

    @ApiOperation(value = "GetSubnivelById",
            notes = "Obtiene un json del subnivel por id pasado por parametro")
    @GetMapping(path = "/{id}")
    public Optional<Subnivel> GetSubnivelById(@PathVariable("id") int id) {
        return subnivelService.GetSubnivelById(id);
    }

    @ApiOperation(value = "SaveSubnivel",
            notes = "Registra un subnivel y devuelve el objeto registrado en json")
    @PostMapping()
    public Subnivel SaveSubnivel(@RequestBody Subnivel subnivel) {
        return subnivelService.SaveAndUpdateSubnivel(subnivel);
    }

    @ApiOperation(value = "UpdateSubnivel",
            notes = "Actualiza un subnivel y devuelve el objeto modificado en json")
    @PutMapping
    public Subnivel UpdateSubnivel(@RequestBody Subnivel subnivel) {
        return subnivelService.SaveAndUpdateSubnivel(subnivel);
    }

    @ApiOperation(value = "DeleteSubnivel",
            notes = "Elimina un subnivel por id pasado por parametro y devuelve un booleano")
    @DeleteMapping(path = "/{id}")
    public boolean DeleteSubnivel(@PathVariable("id") int id) {
        return subnivelService.DeleteSubnivelById(id);
    }

    @ApiOperation(value = "GetSubnivelByNivel",
            notes = "Obtiene un array json de los subniveles registrados por nivel")
    @PostMapping(path = "/bynivel")
    public ArrayList<Subnivel> GetSubnivelByNivel(@RequestBody Nivel nivel) {
        return subnivelService.GetSubnivelByNivel(nivel);
    }

    @ApiOperation(value = "GetSubnivelByNombre",
            notes = "Obtiene un json del nivel registrado por nombre")
    @GetMapping(path = "/byname")
    public Optional<Subnivel> GetSubnivelByNombre(@RequestParam("name") String nombre) {
        return subnivelService.GetSubnivelByNombre(nombre);
    }
}
