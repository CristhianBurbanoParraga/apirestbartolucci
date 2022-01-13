/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.models.Usuario;
import com.example.apirestbartolucci.repositories.UsuarioRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<Usuario> GetAllUsuarios() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    public Optional<Usuario> GetUsuarioById(int id) {
        return usuarioRepository.findById(id);
    }

    public Usuario SaveAndUpdateUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean DeleteUsuarioById(int id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<Usuario> LoginByUsuarioAndClave(String usuario, String clave) {
        return usuarioRepository.findByUsuarioAndClave(usuario, clave);
    }
}
