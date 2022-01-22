/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.controllers;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.dtos.usuario.UsuarioCredentialsDto;
import com.example.apirestbartolucci.dtos.usuario.UsuarioSaveDto;
import com.example.apirestbartolucci.dtos.usuario.UsuarioUpdateCredentialsDto;
import com.example.apirestbartolucci.dtos.usuario.UsuarioUpdateDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.models.Usuario;
import com.example.apirestbartolucci.services.UsuarioService;
import java.util.ArrayList;
import java.util.Optional;
import javax.mail.SendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> GetAll() {
        ArrayList<Usuario> usuarios = usuarioService.GetAllUsuarios();
        if (usuarios.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(usuarios, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        Optional<Usuario> usuario = usuarioService.GetUsuarioById(id);
        if (usuario.isPresent()) {
            return new ResponseEntity(usuario,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No existe registro con id: "
                    + String.valueOf(id)), HttpStatus.OK);
        }

    }

    @GetMapping(path = "/byStatusAccount")
    public ResponseEntity<?> GetAllAccountsByStatus(
            @RequestParam("activo") boolean activo) {
        ArrayList<Usuario> usuarios = usuarioService.GetAllUsuariosByActivo(activo);
        if (usuarios.isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay registros"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(usuarios, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> GetByCredentials(
            @RequestBody UsuarioCredentialsDto credentiales) {
        Optional<Usuario> optionalUsuario
                = usuarioService.LoginByUsuarioAndClave(credentiales);
        if (optionalUsuario.isPresent()) {
            return new ResponseEntity(optionalUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Acceso denegado, "
                    + "posibles causas: \nCredenciales "
                    + "incorrectas ó \nCuenta desactivada"), HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(
            @RequestBody UsuarioSaveDto usuarioSaveDto) {
        if (usuarioService.SaveUsuario(usuarioSaveDto) != null) {
            return new ResponseEntity(usuarioSaveDto, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Error de registro, posibles "
                    + "causas: \nCampos existentes de 'usuario' o 'correo' o "
                    + "'telefono' ó \nSi es una cuenta estudiantil, el docente "
                    + "seleccionado no existe"), HttpStatus.OK);
        }
    }

    @PostMapping(path = "recoveryEmail")
    public ResponseEntity<?> SendEmailRecovery(
            @RequestParam("correo") String correo) throws SendFailedException {
        String message = usuarioService.SendEmailByRecoveryCredentials(correo);
        //String auxco = correo;
        return new ResponseEntity(new Mensaje(message), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody UsuarioUpdateDto usuarioUpdateDto) {
        if (usuarioService.UpdateUsuario(usuarioUpdateDto) != null) {
            return new ResponseEntity(usuarioUpdateDto, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Usuario con id: "
                    + String.valueOf(usuarioUpdateDto.getId())
                    + " inexistente"), HttpStatus.OK);
        }
    }

    @PutMapping(path = "/onlyCredentials")
    public ResponseEntity<?> UpdateOnlyCredentials(
            @RequestBody UsuarioUpdateCredentialsDto usuarioUpdateCredentialsDto) {
        Usuario usuario
                = usuarioService.UpdateCredentials(usuarioUpdateCredentialsDto);
        if (usuario != null) {
            return new ResponseEntity(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Usuario con id: "
                    + String.valueOf(usuarioUpdateCredentialsDto.getId())
                    + " inexistente"), HttpStatus.OK);
        }
    }

    @PutMapping(path = "/changeStatusAccount")
    public Mensaje ChangeUsuarioStatus(@RequestParam("id") int id,
            @RequestParam("activo") boolean activo) {
        return new Mensaje(usuarioService.ChangeUsuarioStatus(id, activo));
    }

}
