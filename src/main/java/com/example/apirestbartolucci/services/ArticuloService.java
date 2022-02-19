/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.articulo.ArticuloMessageDto;
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

    public ArticuloMessageDto GetAllArticulos() {
        ArrayList<Articulo> articulos
                = (ArrayList<Articulo>) articuloRepository.findAll();
        if (articulos.isEmpty()) {
            return new ArticuloMessageDto(false, "No hay registros", null, null);
        } else {
            return new ArticuloMessageDto(true, "Ok", null, articulos);
        }
    }

    public ArticuloMessageDto GetArticuloById(int id) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        if (articulo.isPresent()) {
            return new ArticuloMessageDto(true, "Ok", articulo.get(), null);
        } else {
            return new ArticuloMessageDto(false, "No existe articulo con Id: "
                    + id, null, null);
        }
    }

    public ArticuloMessageDto GetArticuloByNombre(String nombre) {
        Optional<Articulo> articulo = articuloRepository.findByNombre(nombre);
        if (articulo.isPresent()) {
            return new ArticuloMessageDto(true, "Ok", articulo.get(), null);
        } else {
            return new ArticuloMessageDto(false, "No existe articulo con Nombre: "
                    + nombre, null, null);
        }
    }

    public ArticuloMessageDto GetArticuloByStatus(boolean activo) {
        ArrayList<Articulo> articulos
                = (ArrayList<Articulo>) articuloRepository.findByActivo(activo);
        if (articulos.isEmpty()) {
            return new ArticuloMessageDto(false, "No hay articulos con Estado: "
                    + activo, null, null);
        } else {
            return new ArticuloMessageDto(true, "Ok", null, articulos);
        }
    }

    public ArticuloMessageDto SaveArticulo(ArticuloSaveDto articuloSaveDto) {
        if (articuloSaveDto.getMultimedia().getPublicid() == null
                || articuloSaveDto.getMultimedia().getUrl() == null) {
            return new ArticuloMessageDto(false, "Los campos PublicId y Url no "
                    + "pueden ser nulos", null, null);
        } else {
            Optional<Articulo> articulo = articuloRepository.findByNombre(
                    articuloSaveDto.getNombre());
            if (articulo.isPresent()) {
                return new ArticuloMessageDto(false, "Ya existe un articulo "
                        + "registrado con el Nombre: " + articuloSaveDto.getNombre(),
                        null, null);
            } else {
                Articulo newarticulo = new Articulo(0,
                        articuloSaveDto.getNombre(),
                        articuloSaveDto.getCosto(),
                        articuloSaveDto.getMultimedia().getPublicid(),
                        articuloSaveDto.getMultimedia().getUrl(), true, null);
                return new ArticuloMessageDto(true, "Ok",
                        articuloRepository.save(newarticulo), null);
            }
        }
    }

    public ArticuloMessageDto UpdateArticulo(ArticuloUpdateDto articuloUpdateDto) {
        if (articuloUpdateDto.getMultimedia().getPublicid() == null
                || articuloUpdateDto.getMultimedia().getUrl() == null) {
            return new ArticuloMessageDto(false, "Los campos PublicId y Url no "
                    + "pueden ser nulos", null, null);
        } else {
            Optional<Articulo> articulo
                    = articuloRepository.findById(articuloUpdateDto.getId());
            if (articulo.isPresent()) {
                articulo.get().setNombre(articuloUpdateDto.getNombre());
                articulo.get().setCosto(articuloUpdateDto.getCosto());
                articulo.get().setPublicid(articuloUpdateDto.getMultimedia().getPublicid());
                articulo.get().setUrl(articuloUpdateDto.getMultimedia().getUrl());
                articulo.get().setActivo(articuloUpdateDto.isActivo());
                return new ArticuloMessageDto(true, "Ok",
                        articuloRepository.save(articulo.get()), null);
            } else {
                return new ArticuloMessageDto(false, "Id de Articulo inexistente",
                        null, null);
            }
        }
    }

}
