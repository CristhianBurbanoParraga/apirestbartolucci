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

    @GetMapping(path = "/onlyCredentials")
    public ResponseEntity<ArrayList<?>> GetAllUsusariosOnlyCredentials() {
        return new ResponseEntity(
                usuarioService.GetAllUsuariosOnlyCrendentials(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ArrayList<?>> GetAllUsuarios() {
        return new ResponseEntity(usuarioService.GetAllUsuarios(),
                HttpStatus.OK);
    }

    @GetMapping(path = "/onlyCredentials/{id}")
    public ResponseEntity<?> GetUsuarioOnlyCredentialsById(
            @PathVariable("id") int id) {
        return new ResponseEntity(
                usuarioService.GetUsuarioOnlyCrendentialsById(id),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> GetUsuarioById(@PathVariable("id") int id) {
        return new ResponseEntity(usuarioService.GetUsuarioById(id),
                HttpStatus.OK);
    }

    @GetMapping(path = "/byStatusAccount")
    public ResponseEntity<ArrayList<?>> GetAllAccountsByStatus(
            @RequestParam("activo") boolean activo) {
        return new ResponseEntity(usuarioService.GetAllUsuariosByActivo(activo),
                HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> GetUsuarioByCredentials(
            @RequestBody UsuarioCredentialsDto credentiales) {
        Optional<Usuario> optionalUsuario
                = usuarioService.LoginByUsuarioAndClave(credentiales);
        if (optionalUsuario.isPresent()) {
            return new ResponseEntity(optionalUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Error: Credenciales "
                    + "incorrectas o Cuenta desactivada"), HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> SaveUsuario(
            @RequestBody UsuarioSaveDto usuarioSaveDto) {
        if (usuarioService.SaveUsuario(usuarioSaveDto) != null) {
            return new ResponseEntity(usuarioSaveDto, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("Error: Campos existentes "
                    + "'usuario' o 'correo' o 'telefono'"), HttpStatus.OK);
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
    public ResponseEntity<?> UpdateUsuario(
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
