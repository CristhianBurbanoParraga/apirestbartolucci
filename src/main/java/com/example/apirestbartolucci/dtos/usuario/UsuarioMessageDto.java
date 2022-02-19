/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.dtos.usuario;

import com.example.apirestbartolucci.models.Usuario;
import java.util.ArrayList;

/**
 *
 * @author criss
 */
public class UsuarioMessageDto {

    private boolean status;
    private String message;
    private Usuario usuario;
    private UsuarioSaveDto saveDto;
    private UsuarioUpdateDto updateDto;
    private ArrayList<Usuario> usuarios;

    public UsuarioMessageDto() {
    }

    public UsuarioMessageDto(boolean status, String message, Usuario usuario,
            UsuarioSaveDto saveDto, UsuarioUpdateDto updateDto,
            ArrayList<Usuario> usuarios) {
        this.status = status;
        this.message = message;
        this.usuario = usuario;
        this.saveDto = saveDto;
        this.updateDto = updateDto;
        this.usuarios = usuarios;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioSaveDto getSaveDto() {
        return saveDto;
    }

    public void setSaveDto(UsuarioSaveDto saveDto) {
        this.saveDto = saveDto;
    }

    public UsuarioUpdateDto getUpdateDto() {
        return updateDto;
    }

    public void setUpdateDto(UsuarioUpdateDto updateDto) {
        this.updateDto = updateDto;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
