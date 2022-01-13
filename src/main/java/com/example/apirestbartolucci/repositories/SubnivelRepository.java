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
import com.example.apirestbartolucci.models.Subnivel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubnivelRepository extends CrudRepository<Subnivel, Integer> {

    public ArrayList<Subnivel> findSubnivelByNivel(Nivel nivel);

    public Optional<Subnivel> findSubnivelByNombre(String nombre);
}
