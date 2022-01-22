/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

import com.example.apirestbartolucci.models.Estudiante;
import com.example.apirestbartolucci.models.Inventario;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author criss
 */
@Repository
public interface InventarioRepository extends CrudRepository<Inventario, Long> {

    public ArrayList<Inventario> findByEstudiante(Estudiante estudiante);

    public ArrayList<Inventario> findByEstudianteAndSeleccionado(
            Estudiante estudiante, boolean seleccionado);

    public ArrayList<Inventario> findByEstudianteAndActivo(
            Estudiante estudiante, boolean activo);

}
