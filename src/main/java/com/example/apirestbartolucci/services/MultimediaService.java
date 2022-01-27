/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.multimedia.MultimediaSaveDto;
import com.example.apirestbartolucci.dtos.multimedia.MultimediaUpdateDto;
import com.example.apirestbartolucci.dtos.multimedia.OtherMultimediaDto;
import com.example.apirestbartolucci.models.Contenido;
import com.example.apirestbartolucci.models.Multimedia;
import com.example.apirestbartolucci.repositories.ContenidoRepository;
import com.example.apirestbartolucci.repositories.MultimediaRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author criss
 */
@Service
public class MultimediaService {
    
    @Autowired
    CloudinaryService cloudinaryService;
    
    @Autowired
    MultimediaRepository multimediaRepository;
    
    @Autowired
    ContenidoRepository contenidoRepository;
    
    public ArrayList<Multimedia> GetAllMultimedias() {
        return (ArrayList<Multimedia>) multimediaRepository.findAll();
    }
    
    public Optional<Multimedia> GetmultimediaById(long id) {
        return multimediaRepository.findById(id);
    }
    
    public ArrayList<Multimedia> GetMultimediaByIdContenido(long idContenido) {
        Optional<Contenido> contenido
                = contenidoRepository.findById(idContenido);
        if (contenido.isPresent()) {
            return multimediaRepository.findByContenido(contenido.get());
        } else {
            return new ArrayList<Multimedia>();
        }
    }
    
    public OtherMultimediaDto SaveOtherMultimedia(MultipartFile multipartFile) {
        try {
            Map map = cloudinaryService.upload(multipartFile);
            OtherMultimediaDto otherMultimediaDto
                    = new OtherMultimediaDto((String) map.get("public_id"),
                            (String) map.get("url"));
            return otherMultimediaDto;
        } catch (IOException ioe) {
            return null;
        }
    }
    
    public Multimedia SaveMultimedia(MultimediaSaveDto multimediaSaveDto) {
        if (multimediaSaveDto.getMultimedia().getPublicid() == null
                || multimediaSaveDto.getMultimedia().getUrl() == null) {
            return null;
        } else {
            Optional<Contenido> contenido = contenidoRepository.findById(
                    multimediaSaveDto.getIdContenido());
            if (contenido.isPresent()) {
                Multimedia multimedia = new Multimedia(0,
                        contenido.get(),
                        multimediaSaveDto.getDescripcion(),
                        multimediaSaveDto.getMultimedia().getPublicid(),
                        multimediaSaveDto.getMultimedia().getUrl(),
                        multimediaSaveDto.getTipo(),
                        multimediaSaveDto.isIsInicial());
                return multimediaRepository.save(multimedia);
            } else {
                return null;
            }
        }
    }
    
    public Multimedia UpdateMultimedia(
            MultimediaUpdateDto multimediaUpdateDto) {
        Optional<Multimedia> multimedia
                = multimediaRepository.findById(multimediaUpdateDto.getId());
        if (multimedia.isPresent()) {
            Optional<Contenido> contenido
                    = contenidoRepository.findById(
                            multimediaUpdateDto.getIdContenido());
            if (contenido.isPresent()) {
                multimedia.get().setContenido(contenido.get());
                multimedia.get().setDescripcion(
                        multimediaUpdateDto.getDescripcion());
                multimedia.get().setTipo(multimediaUpdateDto.getTipo());
                multimedia.get().setInicial(multimediaUpdateDto.isIsInicial());
                return multimediaRepository.save(multimedia.get());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
