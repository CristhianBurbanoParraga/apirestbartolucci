/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

import com.example.apirestbartolucci.dtos.multimedia.OtherMultimediaDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    MultimediaService multimediaService;

    @PostMapping(path = "/saveOtherMedia",
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

}
