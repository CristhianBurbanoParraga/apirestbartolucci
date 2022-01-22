/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.articulo.ArticuloSaveDto;
import com.example.apirestbartolucci.dtos.articulo.ArticuloUpdateDto;
import com.example.apirestbartolucci.models.Articulo;
import com.example.apirestbartolucci.repositories.ArticuloRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;

    public ArrayList<Articulo> GetAllArticulos() {
        return (ArrayList<Articulo>) articuloRepository.findAll();
    }

    public Optional<Articulo> GetArticuloById(int id) {
        return articuloRepository.findById(id);
    }

    public Optional<Articulo> GetArticuloByNombre(String nombre) {
        return articuloRepository.findByNombre(nombre);
    }

    public ArrayList<Articulo> GetArticuloByStatus(boolean activo) {
        return (ArrayList<Articulo>) articuloRepository.findByActivo(activo);
    }

    public Articulo SaveArticulo(ArticuloSaveDto articuloSaveDto) {
        if (articuloSaveDto.getMultimedia().getPublicid() == null
                && articuloSaveDto.getMultimedia().getUrl() == null) {
            return null;
        } else {
            Optional<Articulo> articulo = articuloRepository.findByNombre(
                    articuloSaveDto.getNombre());
            if (articulo.isPresent()) {
                return null;
            } else {
                Articulo newarticulo = new Articulo(0,
                        articuloSaveDto.getNombre(),
                        articuloSaveDto.getCosto(),
                        articuloSaveDto.getMultimedia().getPublicid(),
                        articuloSaveDto.getMultimedia().getUrl(), true, null);
                return articuloRepository.save(newarticulo);
            }
        }
    }

    public Articulo UpdateArticulo(ArticuloUpdateDto articuloUpdateDto) {
        Optional<Articulo> articulo
                = articuloRepository.findById(articuloUpdateDto.getId());
        if (articulo.isPresent()) {
            articulo.get().setNombre(articuloUpdateDto.getNombre());
            articulo.get().setCosto(articuloUpdateDto.getCosto());
            articulo.get().setActivo(articuloUpdateDto.isActivo());
            return articuloRepository.save(articulo.get());
        } else {
            return null;
        }
    }

}
