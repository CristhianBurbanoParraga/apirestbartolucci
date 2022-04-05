/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.multimedia.MultimediaMessageDto;
import com.example.apirestbartolucci.dtos.multimedia.MultimediaSaveDto;
import com.example.apirestbartolucci.dtos.multimedia.MultimediaUpdateDto;
import com.example.apirestbartolucci.dtos.multimedia.OtherMultimediaDto;
import com.example.apirestbartolucci.models.Contenido;
import com.example.apirestbartolucci.models.Multimedia;
import com.example.apirestbartolucci.repositories.ContenidoRepository;
import com.example.apirestbartolucci.repositories.MultimediaRepository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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

    public MultimediaMessageDto GetAllMultimedias() {
        ArrayList<Multimedia> multimedias
                = (ArrayList<Multimedia>) multimediaRepository.findAll();
        if (multimedias.isEmpty()) {
            return new MultimediaMessageDto(false, "No hay registros", null, null, new ArrayList<>());
        } else {
            return new MultimediaMessageDto(true, "Ok", null, null, multimedias);
        }
    }

    public MultimediaMessageDto GetmultimediaById(long id) {
        Optional<Multimedia> mulimedia = multimediaRepository.findById(id);
        if (mulimedia.isPresent()) {
            return new MultimediaMessageDto(true, "Ok", mulimedia.get(), null, null);
        } else {
            return new MultimediaMessageDto(false, "No existe un multmedia con Id: "
                    + id, null, null, null);
        }
    }

    public MultimediaMessageDto GetMultimediaByIdContenido(long idContenido) {
        Optional<Contenido> contenido
                = contenidoRepository.findById(idContenido);
        if (contenido.isPresent()) {
            ArrayList<Multimedia> multimedias
                    = multimediaRepository.findByContenido(contenido.get());
            if (multimedias.isEmpty()) {
                return new MultimediaMessageDto(false, "No hay registros de multmedia "
                        + "con Id de contenido: " + idContenido, null, null, new ArrayList<>());
            } else {
                return new MultimediaMessageDto(true, "Ok", null, null, multimedias);
            }
        } else {
            return new MultimediaMessageDto(false, "Id de contenido inexistente",
                    null, null, new ArrayList<>());
        }
    }

    public MultimediaMessageDto SaveOtherMultimedia(MultipartFile multipartFile) {
        try {
            Map map = cloudinaryService.upload(multipartFile);
            OtherMultimediaDto otherMultimediaDto
                    = new OtherMultimediaDto((String) map.get("public_id"),
                            (String) map.get("secure_url"));
            return new MultimediaMessageDto(true, "Ok",
                    null, otherMultimediaDto, null);
        } catch (IOException ioe) {
            return new MultimediaMessageDto(false, ioe.getMessage(),
                    null, null, null);
        }
    }

    public MultimediaMessageDto SaveOtherMultimediaServer(
            MultipartFile multipartFile) {
        try {
            String uuid = UUID.randomUUID().toString().replace("-", "")
                    .substring(0, 30);
            String newName = uuid + multipartFile.getOriginalFilename()
                    .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String path = "D:/Imagenes/" + newName;
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            multipartFile.transferTo(file);
            return new MultimediaMessageDto(true, "Ok",
                    null, new OtherMultimediaDto(uuid, path), null);
        } catch (IOException ioe) {
            return new MultimediaMessageDto(false, ioe.getMessage(),
                    null, null, null);
        } catch (IllegalStateException i) {
            return new MultimediaMessageDto(false, i.getMessage(),
                    null, null, null);
        }
    }

    public MultimediaMessageDto SaveMultimedia(MultimediaSaveDto multimediaSaveDto) {
        if (multimediaSaveDto.getMultimedia().getPublicid() == null
                || multimediaSaveDto.getMultimedia().getUrl() == null) {
            return new MultimediaMessageDto(false, "Los campos PublicId y Url no "
                    + "pueden ser nulos", null, null, null);
        } else if (multimediaRepository.findByPublicid(
                multimediaSaveDto.getMultimedia().getPublicid()).isPresent()) {
            return new MultimediaMessageDto(false, "Ya existe un registro con Publicid: "
                    + multimediaSaveDto.getMultimedia().getPublicid(), null, null, null);
        } else {
            Optional<Contenido> contenido = contenidoRepository.findById(
                    multimediaSaveDto.getIdContenido());
            if (contenido.isPresent()) {
                ArrayList<Multimedia> multimedias
                        = multimediaRepository.findByContenido(contenido.get());
                if (multimedias.size() < 3) {
                    boolean init = false;
                    if (multimediaSaveDto.isIsInicial()) {
                        for (int i = 0; i < multimedias.size(); i++) {
                            if (multimedias.get(i).isInicial()) {
                                init = true;
                                break;
                            }
                        }
                        if (init) {
                            return new MultimediaMessageDto(false, "Ya existe un "
                                    + "multimedia inicial", null, null, null);
                        } else {
                            Multimedia multimedia = new Multimedia(0,
                                    contenido.get(),
                                    multimediaSaveDto.getDescripcion(),
                                    multimediaSaveDto.getMultimedia().getPublicid(),
                                    multimediaSaveDto.getMultimedia().getUrl(),
                                    multimediaSaveDto.getTipo(),
                                    multimediaSaveDto.isIsInicial());
                            return new MultimediaMessageDto(true, "Ok",
                                    multimediaRepository.save(multimedia), null, null);
                        }
                    } else {
                        Multimedia multimedia = new Multimedia(0,
                                contenido.get(),
                                multimediaSaveDto.getDescripcion(),
                                multimediaSaveDto.getMultimedia().getPublicid(),
                                multimediaSaveDto.getMultimedia().getUrl(),
                                multimediaSaveDto.getTipo(),
                                multimediaSaveDto.isIsInicial());
                        return new MultimediaMessageDto(true, "Ok",
                                multimediaRepository.save(multimedia), null, null);
                    }
                } else {
                    return new MultimediaMessageDto(false, "Este Contenido ya tiene"
                            + " las 3 multimedias permitidas", null, null, null);
                }
            } else {
                return new MultimediaMessageDto(false, "No existe contenido con Id: "
                        + multimediaSaveDto.getIdContenido(), null, null, null);
            }
        }
    }

    public MultimediaMessageDto UpdateMultimedia(
            MultimediaUpdateDto multimediaUpdateDto) {
        if (multimediaUpdateDto.getMultimedia().getPublicid() == null
                || multimediaUpdateDto.getMultimedia().getUrl() == null) {
            return new MultimediaMessageDto(false, "Los campos PublicId y Url no "
                    + "pueden ser nulos", null, null, null);
        } else {
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
                    multimedia.get().setPublicid(
                            multimediaUpdateDto.getMultimedia().getPublicid());
                    multimedia.get().setUrl(
                            multimediaUpdateDto.getMultimedia().getUrl());
                    return new MultimediaMessageDto(true, "Ok",
                            multimediaRepository.save(multimedia.get()), null, null);
                } else {
                    return new MultimediaMessageDto(false, "No existe contenido con Id: "
                            + multimediaUpdateDto.getIdContenido(), null, null, null);
                }
            } else {
                return new MultimediaMessageDto(false, "Id de multimedia inexistente",
                        null, null, null);
            }
        }
    }

}
