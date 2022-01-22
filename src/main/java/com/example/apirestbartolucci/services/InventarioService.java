/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.models.Articulo;
import com.example.apirestbartolucci.models.Estudiante;
import com.example.apirestbartolucci.models.Inventario;
import com.example.apirestbartolucci.repositories.ArticuloRepository;
import com.example.apirestbartolucci.repositories.EstudianteRepository;
import com.example.apirestbartolucci.repositories.InventarioRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import jdk.nashorn.internal.objects.NativeArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author criss
 */
@Service
public class InventarioService {

    @Autowired
    InventarioRepository inventarioRepository;

    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    ArticuloRepository articuloRepository;

    public ArrayList<Inventario> GetAllInventarios() {
        return (ArrayList<Inventario>) inventarioRepository.findAll();
    }

    public Optional<Inventario> GetInventrioById(long id) {
        return inventarioRepository.findById(id);
    }

    public ArrayList<Inventario> GetInventarioByIdEstudiante(int id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isPresent()) {
            return inventarioRepository.findByEstudiante(estudiante.get());
        } else {
            return new ArrayList<Inventario>();
        }
    }

    public ArrayList<Inventario> GetInventarioByIdEstudianteAndSelect(int id,
            boolean seleccionado) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isPresent()) {
            return inventarioRepository.findByEstudianteAndSeleccionado(
                    estudiante.get(), seleccionado);
        } else {
            return new ArrayList<Inventario>();
        }
    }

    public ArrayList<Inventario> GetInventarioByIdEstudianteAndActivo(int id,
            boolean activo) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isPresent()) {
            return inventarioRepository.findByEstudianteAndActivo(
                    estudiante.get(), activo);
        } else {
            return new ArrayList<Inventario>();
        }
    }

    public String ChangeStatusArticulo(long id, int idEstudiante,
            boolean seleccionado, boolean activo) {
        Optional<Inventario> inventario = inventarioRepository.findById(id);
        if (inventario.isPresent()) {
            Optional<Estudiante> estudiante
                    = estudianteRepository.findById(idEstudiante);
            if (estudiante.isPresent()) {
                ArrayList<Inventario> inventarios = inventarioRepository
                        .findByEstudiante(estudiante.get());
                if (seleccionado) {
                    for (int i = 0; i < inventarios.size(); i++) {
                        if (inventarios.get(i).isSeleccionado()) {
                            inventarios.get(i).setSeleccionado(false);
                            inventarioRepository.save(inventarios.get(i));
                            break;
                        } else {
                            continue;
                        }
                    }
                }
                inventario.get().setSeleccionado(seleccionado);
                inventario.get().setActivo(activo);
                inventarioRepository.save(inventario.get());
                return "Cambio en Inventario con id: "
                        + String.valueOf(id) + " ,Selected a: "
                        + String.valueOf(seleccionado) + ",Status a: "
                        + String.valueOf(activo);
            } else {
                return "Estudiante con id: " + String.valueOf(id)
                        + "inexistente";
            }
        } else {
            return "Inventario con id: " + String.valueOf(id) + "inexistente";
        }
    }

    public Inventario SaveInventario(int idEstudiante, int idArticulo) {
        Optional<Estudiante> estudiante
                = estudianteRepository.findById(idEstudiante);
        if (estudiante.isPresent()) {
            Optional<Articulo> articulo
                    = articuloRepository.findById(idArticulo);
            if (articulo.isPresent()) {
                int stock = estudiante.get().getStockcaritas();
                int costo = articulo.get().getCosto();
                if (stock >= costo) {
                    Inventario inventario = new Inventario(0,
                            estudiante.get(),
                            articulo.get(),
                            new Date(),
                            false, true);
                    estudiante.get().setStockcaritas(stock - costo);
                    estudianteRepository.save(estudiante.get());
                    return inventarioRepository.save(inventario);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
