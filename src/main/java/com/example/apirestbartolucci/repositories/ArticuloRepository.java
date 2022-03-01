/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

import com.example.apirestbartolucci.models.Articulo;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author criss
 */
@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, Integer> {

    public Optional<Articulo> findByNombre(String nombre);

    public Optional<Articulo> findByPublicid(String publicid);

    public ArrayList<Articulo> findByActivo(boolean activo);
}
