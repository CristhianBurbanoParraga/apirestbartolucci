/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Contenido;
import com.example.apirestbartolucci.repositories.ContenidoRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContenidoService {

    @Autowired
    ContenidoRepository contenidoRepository;

    public ArrayList<Contenido> GetAllContenidos() {
        return (ArrayList<Contenido>) contenidoRepository.findAll();
    }

    public Optional<Contenido> GetContenidoById(int id) {
        return contenidoRepository.findById(id);
    }

    public Contenido SaveAndUpdateContenido(Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    public boolean DeleteContenidoById(int id) {
        try {
            contenidoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Contenido> GetContenidoByActividad(Actividad actividad) {
        return (ArrayList<Contenido>) contenidoRepository.findByActividad(actividad);
    }

    public ArrayList<Contenido> GetContenidoByTipo(String tipo) {
        return (ArrayList<Contenido>) contenidoRepository.findByTipo(tipo);
    }

}
