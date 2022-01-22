/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

import com.example.apirestbartolucci.models.Docente;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author criss
 */
@Repository
public interface DocenteRepository extends CrudRepository<Docente, Integer> {

    public Optional<Docente> findByNombresAndApellidos(String nombres, String apellidos);

    public Optional<Docente> findByTelefono(String telefono);

    public Optional<Docente> findByCorreo(String correo);
}
