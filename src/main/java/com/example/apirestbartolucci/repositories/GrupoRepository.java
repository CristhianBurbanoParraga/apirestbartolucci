/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

import com.example.apirestbartolucci.dtos.grupo.GrupoDto;
import com.example.apirestbartolucci.models.Docente;
import com.example.apirestbartolucci.models.Estudiante;
import com.example.apirestbartolucci.models.Grupo;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author criss
 */
@Repository
public interface GrupoRepository extends CrudRepository<Grupo, Integer> {

    public ArrayList<Grupo> findByFecharegistro(Date fecharegistro);

    public ArrayList<Grupo> findByDocente(Docente docente);

    public ArrayList<Grupo> findByEstudiante(Estudiante estudiante);

    public ArrayList<Grupo> findByActivo(boolean activo);

}
