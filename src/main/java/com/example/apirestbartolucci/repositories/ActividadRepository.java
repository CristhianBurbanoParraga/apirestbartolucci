/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

import com.example.apirestbartolucci.models.Actividad;
import com.example.apirestbartolucci.models.Docente;
import com.example.apirestbartolucci.models.Subnivel;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author criss
 */
@Repository
public interface ActividadRepository extends CrudRepository<Actividad, Integer> {

    public ArrayList<Actividad> findByOrderByIdAsc();

    public ArrayList<Actividad> findBySubnivel(Subnivel subnivel);

    public ArrayList<Actividad> findByDocente(Docente docente);

    public ArrayList<Actividad> findByNombre(String nombre);

    public ArrayList<Actividad> findByActivo(boolean activo);

    public ArrayList<Actividad> findBySubnivelAndDocente(Subnivel subnivel, Docente docente);

    public ArrayList<Actividad> findByDocenteAndTipo(Docente docente, String tipo);
}
