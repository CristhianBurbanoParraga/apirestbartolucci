/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.models.Multimedia;
import com.example.apirestbartolucci.repositories.MultimediaRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class MultimediaService {

    @Autowired
    MultimediaRepository multimediaRepository;

    public ArrayList<Multimedia> GetAllMultimedias() {
        return (ArrayList<Multimedia>) multimediaRepository.findAll();
    }

    public Optional<Multimedia> GetMultimediaById(int id) {
        return multimediaRepository.findById(id);
    }

    public Multimedia SaveAndUpdateMultimedia(Multimedia multimedia) {
        return multimediaRepository.save(multimedia);
    }

    public boolean DeleteMultimediaById(int id) {
        try {
            multimediaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<Multimedia> GetMultimediaByNombre(String nombre) {
        return multimediaRepository.findByNombre(nombre);
    }

    public Optional<Multimedia> GetMultimediaByPublicid(String publicid) {
        return multimediaRepository.findByPublicid(publicid);
    }

    public Optional<Multimedia> GetMultimediaByUrl(String url) {
        return multimediaRepository.findByUrl(url);
    }

}
