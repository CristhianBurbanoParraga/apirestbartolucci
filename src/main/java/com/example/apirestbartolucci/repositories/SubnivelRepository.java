/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

import com.example.apirestbartolucci.models.Nivel;
import com.example.apirestbartolucci.models.Subnivel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author criss
 */
@Repository
public interface SubnivelRepository extends CrudRepository<Subnivel, Integer> {

    public ArrayList<Subnivel> findByNivel(Nivel nivel, Sort sort);

    public Optional<Subnivel> findByNombre(String nombre);

    public ArrayList<Subnivel> findByActivo(boolean activo);
}
