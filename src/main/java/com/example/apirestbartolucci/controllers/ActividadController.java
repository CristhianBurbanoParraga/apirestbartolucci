/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Subnivel;
import com.example.apirestbartolucci.services.ActividadService;
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
@RequestMapping(path = "/actividad")
public class ActividadController {

    @Autowired
    ActividadService actividadService;

    @ApiOperation(value = "GetAllActividades",
            notes = "Obtiene un array json de las actividades registtadas")
    @GetMapping()
    public ArrayList<Actividad> GetAllActividades() {
        return actividadService.GetAllActividades();
    }

    @ApiOperation(value = "GetActividadById",
            notes = "Obtiene un json de la actividad por id pasado por parametro")
    @GetMapping(path = "/{id}")
    public Optional<Actividad> GetActividadById(@PathVariable("id") int id) {
        return actividadService.GetActividadById(id);
    }

    @ApiOperation(value = "SaveActividad",
            notes = "Registra una actividad y devuelve el objeto registrado en json")
    @PostMapping()
    public Actividad SaveActividad(@RequestBody Actividad actividad) {
        return actividadService.SaveAndUpdateActividad(actividad);
    }

    @ApiOperation(value = "UpdateActividad",
            notes = "Actualiza una actividad y devuelve el objeto modificado en json")
    @PutMapping
    public Actividad UpdateActividad(@RequestBody Actividad actividad) {
        return actividadService.SaveAndUpdateActividad(actividad);
    }

    @ApiOperation(value = "DeleteActividad",
            notes = "Elimina una actividad por id pasado por parametro y devuelve un booleano")
    @DeleteMapping(path = "/{id}")
    public boolean DeleteActividad(@PathVariable("id") int id) {
        return actividadService.DeleteActividadById(id);
    }

    @ApiOperation(value = "GetActividadBySubnivel",
            notes = "Obtiene un array json de las actividades registradas por subnivel")
    @PostMapping(path = "/bysubnivel")
    public ArrayList<Actividad> GetActividadBySubnivel(@RequestBody Subnivel subnivel) {
        return actividadService.GetActividadBySubnivel(subnivel);
    }

    @ApiOperation(value = "GetActividadByTipo",
            notes = "Obtiene un json de las actividades registradas por tipo")
    @GetMapping(path = "/bytype")
    public ArrayList<Actividad> GetActividadByTipo(@RequestParam("type") String tipo) {
        return actividadService.GetActividadByTipo(tipo);
    }

    @ApiOperation(value = "GetActividadByValor",
            notes = "Obtiene un json de las actividades registradas por valor")
    @GetMapping(path = "/byvalue")
    public ArrayList<Actividad> GetActividadByValor(@RequestParam("value") int valor) {
        return actividadService.GetActividadByValor(valor);
    }
}
