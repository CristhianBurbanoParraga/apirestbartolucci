/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

import com.example.apirestbartolucci.models.DatosUsuario;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author criss
 */
@Repository
public interface DatosUsuarioRepository extends CrudRepository<DatosUsuario, Integer> {

    public Optional<DatosUsuario> findByCorreo(String correo);

    public Optional<DatosUsuario> findByTelefono(String telefono);
}
