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
import com.example.apirestbartolucci.dtos.usuario.UsuarioMessageDto;
import com.example.apirestbartolucci.dtos.usuario.UsuarioSaveDto;
import com.example.apirestbartolucci.dtos.usuario.UsuarioUpdateDto;
import com.example.apirestbartolucci.models.Mensaje;
import com.example.apirestbartolucci.services.UsuarioService;
import javax.mail.SendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(path = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<?> GetAll() {
        UsuarioMessageDto usuarios = usuarioService.GetAllUsuarios();
        if (usuarios.isStatus()) {
            return new ResponseEntity(usuarios.getUsuarios(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(usuarios.getUsuarios(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") int id) {
        UsuarioMessageDto usuario = usuarioService.GetUsuarioById(id);
        if (usuario.isStatus()) {
            return new ResponseEntity(usuario.getUsuario(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(usuario.getMessage()),
                    HttpStatus.OK);
        }

    }

    @GetMapping(path = "/byTipo")
    public ResponseEntity<?> GetByTipo(
            @RequestParam("tipo") String tipo) {
        UsuarioMessageDto usuarios = usuarioService.GetAllUsuarioByTipo(tipo);
        if (usuarios.isStatus()) {
            return new ResponseEntity(usuarios.getUsuariosDto(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(usuarios.getUsuariosDto(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/byStatusAccount")
    public ResponseEntity<?> GetAllAccountsByStatus(
            @RequestParam("activo") boolean activo) {
        UsuarioMessageDto usuarios = usuarioService.GetAllUsuariosByActivo(activo);
        if (usuarios.isStatus()) {
            return new ResponseEntity(usuarios.getUsuarios(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(usuarios.getUsuarios(),
                    HttpStatus.OK);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> GetByCredentials(
            @RequestBody UsuarioCredentialsDto credentiales) {
        UsuarioMessageDto optionalUsuario
                = usuarioService.LoginByUsuarioAndClave(credentiales);
        if (optionalUsuario.isStatus()) {
            return new ResponseEntity(optionalUsuario.getUsuarioLoginDto(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(optionalUsuario.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> Save(
            @RequestBody UsuarioSaveDto usuarioSaveDto) {
        UsuarioMessageDto usuario = usuarioService.SaveUsuario(usuarioSaveDto);
        if (usuario.isStatus()) {
            return new ResponseEntity(usuario.getSaveDto(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(usuario.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PostMapping(path = "/recoveryEmail")
    public ResponseEntity<?> SendEmailRecovery(
            @RequestParam("correo") String correo) throws SendFailedException {
        String message = usuarioService.SendEmailByRecoveryCredentials(correo);
        return new ResponseEntity(new Mensaje(message), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> Update(
            @RequestBody UsuarioUpdateDto usuarioUpdateDto) {
        UsuarioMessageDto usuario = usuarioService.UpdateUsuario(usuarioUpdateDto);
        if (usuario.isStatus()) {
            return new ResponseEntity(usuario.getUpdateDto(), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje(usuario.getMessage()), HttpStatus.OK);
        }
    }

}
