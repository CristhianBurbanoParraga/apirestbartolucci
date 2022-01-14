/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.models.Multimedia;
import com.example.apirestbartolucci.services.CloudinaryService;
import com.example.apirestbartolucci.services.MultimediaService;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author criss
 */
@RestController
@RequestMapping(path = "/multimedia")
public class MultimediaController {

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    MultimediaService multimediaService;

    @ApiOperation(value = "GetAllMultimedias",
            notes = "Obtiene un array json de los archivos multimedia registtados")
    @GetMapping()
    public ArrayList<Multimedia> GetAllMultimedias() {
        return multimediaService.GetAllMultimedias();
    }

    @ApiOperation(value = "GetMultimediaById",
            notes = "Obtiene un json del archivo multimedia por id pasado por parametro")
    @GetMapping(path = "{id}")
    public Optional<Multimedia> GetMultimediaById(@PathVariable("id") int id) {
        return multimediaService.GetMultimediaById(id);
    }

    @ApiOperation(value = "SaveMultimedia",
            notes = "Registra un archivo multimedia y devuelve el objeto registrado en json")
    @PostMapping()
    public ResponseEntity<Multimedia> SaveMultimedia(@RequestParam MultipartFile multipartFile,
            @RequestBody Multimedia multimedia) throws IOException {
        Map result = cloudinaryService.upload(multipartFile);
        if (multimedia.getNombre() == null) {
            multimedia.setNombre((String) result.get("original_filename")
                    + "." + (String) result.get("format"));
        }
        multimedia.setPublicid((String) result.get("public_id"));
        multimedia.setUrl((String) result.get("url"));
        multimediaService.SaveAndUpdateMultimedia(multimedia);
        return new ResponseEntity(multimedia, HttpStatus.OK);
    }

    @ApiOperation(value = "DeleteMultimedia",
            notes = "Elimina un archivo multimedia por id pasado por parametro y devuelve un booleano")
    @DeleteMapping(path = "/{id}")
    public boolean DeleteMultimedia(@PathVariable("id") int id) throws IOException {
        Multimedia multimedia = multimediaService.GetMultimediaById(id).get();
        cloudinaryService.delete(multimedia.getPublicid());
        return multimediaService.DeleteMultimediaById(id);
    }

    @ApiOperation(value = "GetMultimediaByNombre",
            notes = "Obtiene un json del archivo multimedia registrado por nombre")
    @GetMapping(path = "/byname")
    public Optional<Multimedia> GetMultimediaByNombre(@PathVariable("name") String nombre) {
        return multimediaService.GetMultimediaByNombre(nombre);
    }

    @ApiOperation(value = "GetMultimediaByPublicid",
            notes = "Obtiene un json del archivo multimedia registrado por publicid")
    @GetMapping(path = "/bypublic-id")
    public Optional<Multimedia> GetMultimediaByPublicid(@PathVariable("publicid") String publicid) {
        return multimediaService.GetMultimediaByPublicid(publicid);
    }

    @ApiOperation(value = "GetMultimediaByUrl",
            notes = "Obtiene un json del archivo multimedia registrado por url")
    @GetMapping(path = "/byurl")
    public Optional<Multimedia> GetMultimediaByUrl(@PathVariable("url") String url) {
        return multimediaService.GetMultimediaByUrl(url);
    }

}
