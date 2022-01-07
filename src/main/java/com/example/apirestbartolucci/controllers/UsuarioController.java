/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.models.Usuario;
import com.example.apirestbartolucci.services.UsuarioService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<Usuario> GetAllUsuarios() {
        return usuarioService.GetAllUsuarios();
    }

    @GetMapping(path = "/{id}")
    public Optional<Usuario> GetUsuarioById(@PathVariable("id") int id) {
        return usuarioService.GetUsuarioById(id);
    }

    @PostMapping()
    public Usuario SaveUsuario(@RequestBody Usuario usuario) {
        return usuarioService.SaveUsuario(usuario);
    }

    @PutMapping
    public Usuario UpdateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.UpdateUsuario(usuario);
    }

    @DeleteMapping(path = "/{id}")
    public boolean DeleteUsuario(@PathVariable("id") int id) {
        return usuarioService.DeleteUsuarioById(id);
    }

    @GetMapping(path = "/login")
    public Optional<Usuario> LoginByUsuarioAndClave(@RequestParam("user") String usuario,
            @RequestParam("password") String clave) {
        return usuarioService.LoginByUsuarioAndClave(usuario, clave);
    }
}
