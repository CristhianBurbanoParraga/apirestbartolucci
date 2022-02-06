/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.models.Nivel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelRepository extends CrudRepository<Nivel, Integer> {

    public ArrayList<Nivel> findByOrderByPrioridadAsc();

    public Optional<Nivel> findByNombre(String nombre);

    public ArrayList<Nivel> findByActivo(boolean activo);
}
