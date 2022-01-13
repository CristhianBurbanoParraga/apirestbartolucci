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
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "GetAllUsuarios",
            notes = "Obtiene un array json de los usuarios registrados")
    @GetMapping()
    public ArrayList<Usuario> GetAllUsuarios() {
        return usuarioService.GetAllUsuarios();
    }

    @ApiOperation(value = "GetUsuarioById",
            notes = "Obtiene un json del usuario por id pasado por parametro")
    @GetMapping(path = "/{id}")
    public Optional<Usuario> GetUsuarioById(@PathVariable("id") int id) {
        return usuarioService.GetUsuarioById(id);
    }

    @ApiOperation(value = "SaveUsuario",
            notes = "Registra un usuario y devuelve el objeto registrado en json")
    @PostMapping()
    public Usuario SaveUsuario(@RequestBody Usuario usuario) {
        return usuarioService.SaveAndUpdateUsuario(usuario);
    }

    @ApiOperation(value = "UpdateUsuario",
            notes = "Actualiza un usuario y devuelve el objeto modificado en json")
    @PutMapping
    public Usuario UpdateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.SaveAndUpdateUsuario(usuario);
    }

    @ApiOperation(value = "DeleteUsuario",
            notes = "Elimina un usuario por id pasado por parametro y devuelve un booleano")
    @DeleteMapping(path = "/{id}")
    public boolean DeleteUsuario(@PathVariable("id") int id) {
        return usuarioService.DeleteUsuarioById(id);
    }

    @ApiOperation(value = "LoginByUsuarioAndClave",
            notes = "Obtiene un usuario por sus credenciales pasadas por parametro y devuelve un json")
    @GetMapping(path = "/login")
    public Optional<Usuario> LoginByUsuarioAndClave(@RequestParam("user") String usuario,
            @RequestParam("password") String clave) {
        return usuarioService.LoginByUsuarioAndClave(usuario, clave);
    }
}
