/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.multimedia.MultimediaMessageDto;
import com.example.apirestbartolucci.dtos.multimedia.MultimediaSaveDto;
import com.example.apirestbartolucci.dtos.multimedia.MultimediaUpdateDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.MultimediaService;
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
        MultimediaMessageDto multimedias
                = multimediaService.GetAllMultimedias();
        if (multimedias.isStatus()) {
            return new ResponseEntity(multimedias.getMultimedias(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(multimedias.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") long id) {
        MultimediaMessageDto multimedia
                = multimediaService.GetmultimediaById(id);
        if (multimedia.isStatus()) {
            return new ResponseEntity(multimedia.getMultimedia(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(multimedia.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byContenido")
    public ResponseEntity<?> GetByContenido(
            @RequestParam("idContenido") long idContenido) {
        MultimediaMessageDto multimeidas
                = multimediaService.GetMultimediaByIdContenido(idContenido);
        if (multimeidas.isStatus()) {
            return new ResponseEntity(multimeidas.getMultimedias(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(multimeidas.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PostMapping(path = "/saveFileMedia",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> SaveOtherMultimedia(
            @RequestParam MultipartFile multipartFile) {
        MultimediaMessageDto otherDto
                = multimediaService.SaveOtherMultimedia(multipartFile);
        if (otherDto.isStatus()) {
            return new ResponseEntity(otherDto.getOtherMultimediaDto(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(otherDto.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PostMapping(path = "/saveFileServer",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> SaveOtherMultimediaServer(
            @RequestParam MultipartFile multipartFile) {
        MultimediaMessageDto otherDto
                = multimediaService.SaveOtherMultimediaServer(multipartFile);
        if (otherDto.isStatus()) {
            return new ResponseEntity(otherDto.getOtherMultimediaDto(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(otherDto.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(
            @RequestBody MultimediaSaveDto multimediaSaveDto) {
        MultimediaMessageDto multimedia
                = multimediaService.SaveMultimedia(multimediaSaveDto);
        if (multimedia.isStatus()) {
            return new ResponseEntity(multimedia.getMultimedia(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(multimedia.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody MultimediaUpdateDto multimediaUpdateDto) {
        MultimediaMessageDto multimedia
                = multimediaService.UpdateMultimedia(multimediaUpdateDto);
        if (multimedia.isStatus()) {
            return new ResponseEntity(multimedia.getMultimedia(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(multimedia.getMessage()),
                    HttpStatus.OK);
        }
    }

}
