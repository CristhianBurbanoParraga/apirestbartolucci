/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirestbartolucci.repositories;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.models.Usuario;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    public Optional<Usuario> findByUsuarioAndClave(String usuario, String clave);
}
