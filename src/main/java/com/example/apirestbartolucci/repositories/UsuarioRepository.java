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
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    public Optional<Usuario> findByUsuario(String usuario);

    public ArrayList<Usuario> findByTipousuario(String tipousuario);

    public ArrayList<Usuario> findByActivo(boolean activo);

    public ArrayList<Usuario> findByUsuarioNot(String usuario);
}
