/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Estudiante;
import com.example.apirestbartolucci.models.Historial;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author criss
 */
@Repository
public interface HistorialRepository extends CrudRepository<Historial, Long> {

    public ArrayList<Historial> findByEstudiante(Estudiante estudiante);

    public ArrayList<Historial> findByActividad(Actividad actividad);

    public Optional<Historial> findByActividadAndEstudiante(Actividad actividad, Estudiante estudiante);
}
