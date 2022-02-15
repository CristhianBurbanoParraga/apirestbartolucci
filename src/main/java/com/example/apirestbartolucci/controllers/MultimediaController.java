/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.multimedia.MultimediaSaveDto;
import com.example.apirestbartolucci.dtos.multimedia.MultimediaUpdateDto;
import com.example.apirestbartolucci.dtos.multimedia.OtherMultimediaDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.models.Multimedia;
import com.example.apirestbartolucci.services.MultimediaService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author criss
 */
@RestController
@RequestMapping(path = "/multimedia", produces = MediaType.APPLICATION_JSON_VALUE)
public class MultimediaController {

    @Autowired
    MultimediaService multimediaService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        ArrayList<Multimedia> multimedias
                = multimediaService.GetAllMultimedias();
        if (multimedias.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(multimedias, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") long id) {
        Optional<Multimedia> multimedia
                = multimediaService.GetmultimediaById(id);
        if (multimedia.isPresent()) {
            return new ResponseEntity(multimedia, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No hay registros con id: "
                    + String.valueOf(id)),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byContenido")
    public ResponseEntity<?> GetByContenido(
            @RequestParam("idContenido") long idContenido) {
        ArrayList<Multimedia> multimeidas
                = multimediaService.GetMultimediaByIdContenido(idContenido);
        if (multimeidas.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros con "
                    + "idContenido: " + String.valueOf(idContenido)),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(multimeidas, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/saveFileMedia",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> SaveOtherMultimedia(
            @RequestParam MultipartFile multipartFile) {
        OtherMultimediaDto otherDto
                = multimediaService.SaveOtherMultimedia(multipartFile);
        if (otherDto == null) {
            return new ResponseEntity(new Mensaje("Error al guardar el archivo"
                    + " multimedia"), HttpStatus.OK);
        } else {
            return new ResponseEntity(otherDto, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(MultimediaSaveDto multimediaSaveDto) {
        Multimedia multimedia
                = multimediaService.SaveMultimedia(multimediaSaveDto);
        if (multimedia == null) {
            return new ResponseEntity(new Mensaje("Error al guardar, posibles "
                    + "causas:\nCampos del archivo media nulos o\nId contenido "
                    + "inexistente"), HttpStatus.OK);
        } else {
            return new ResponseEntity(multimedia, HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(MultimediaUpdateDto multimediaUpdateDto) {
        Multimedia multimedia
                = multimediaService.UpdateMultimedia(multimediaUpdateDto);
        if (multimedia == null) {
            return new ResponseEntity(new Mensaje("Error al guardar, posibles "
                    + "causas:\nId multimidia inexistente o\nId contenido "
                    + "inexistente"), HttpStatus.OK);
        } else {
            return new ResponseEntity(multimedia, HttpStatus.OK);
        }
    }

}
