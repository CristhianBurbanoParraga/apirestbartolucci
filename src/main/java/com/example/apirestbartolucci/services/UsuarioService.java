/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

/**
 *
 * @author criss
 */
import com.example.apirestbartolucci.dtos.usuario.UsuarioCredentialsDto;
import com.example.apirestbartolucci.dtos.usuario.UsuarioSaveDto;
import com.example.apirestbartolucci.dtos.usuario.UsuarioUpdateCredentialsDto;
import com.example.apirestbartolucci.dtos.usuario.UsuarioUpdateDto;
import com.example.apirestbartolucci.models.DatosUsuario;
import com.example.apirestbartolucci.models.Usuario;
import com.example.apirestbartolucci.repositories.DatosUsuarioRepository;
import com.example.apirestbartolucci.repositories.UsuarioRepository;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import javax.mail.SendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DatosUsuarioRepository datosUsuarioRepository;

    @Autowired
    JasyptService jasyptService;

    @Autowired
    EmailService emailService;

    public ArrayList<Usuario> GetAllUsuariosOnlyCrendentials() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    public ArrayList<DatosUsuario> GetAllUsuarios() {
        return (ArrayList<DatosUsuario>) datosUsuarioRepository.findAll();
    }

    public Optional<Usuario> GetUsuarioOnlyCrendentialsById(int id) {
        return usuarioRepository.findById(id);
    }

    public Optional<DatosUsuario> GetUsuarioById(int id) {
        return datosUsuarioRepository.findById(id);
    }

    public ArrayList<Usuario> GetAllUsuariosByActivo(boolean activo) {
        return (ArrayList<Usuario>) usuarioRepository.findByActivo(activo);
    }

    public UsuarioSaveDto SaveUsuario(UsuarioSaveDto usuarioSaveDto) {
        if (ExistsUsuarioSave(usuarioSaveDto.getUsuario(),
                usuarioSaveDto.getCorreo(),
                usuarioSaveDto.getTelefono()) == true) {
            return null;
        } else {
            Usuario usuario = new Usuario();
            usuario.setUsuario(usuarioSaveDto.getUsuario());
            usuario.setClave(jasyptService.EncryptValor(
                    usuarioSaveDto.getClave()));
            usuario.setTipousuario("US");
            usuario.setStockcaritas(0);
            usuario.setActivo(usuarioSaveDto.isActivo());
            int id = usuarioRepository.save(usuario).getId();
            datosUsuarioRepository.save(new DatosUsuario(0,
                    usuario, usuarioSaveDto.getNombres(),
                    usuarioSaveDto.getApellidos(),
                    usuarioSaveDto.getTelefono(),
                    usuarioSaveDto.getCorreo(),
                    usuarioSaveDto.getFechanacimiento()));
            return usuarioSaveDto;
        }
    }

    public UsuarioUpdateDto UpdateUsuario(UsuarioUpdateDto usuarioUpdateDto) {
        Optional<DatosUsuario> datosUsuario
                = datosUsuarioRepository.findById(usuarioUpdateDto.getId());
        if (datosUsuario.isPresent()) {
            datosUsuario.get().setNombres(usuarioUpdateDto.getNombres());
            datosUsuario.get().setApellidos(usuarioUpdateDto
                    .getApellidos());
            datosUsuario.get().setTelefono(usuarioUpdateDto.getTelefono());
            datosUsuario.get().setCorreo(usuarioUpdateDto.getCorreo());
            datosUsuario.get().setFechanacimiento(usuarioUpdateDto
                    .getFechanacimiento());
            datosUsuarioRepository.save(datosUsuario.get());
            return usuarioUpdateDto;
        } else {
            return null;
        }
    }

    public Usuario UpdateCredentials(UsuarioUpdateCredentialsDto credenciales) {
        Optional<Usuario> usuario
                = usuarioRepository.findById(credenciales.getId());
        if (usuario.isPresent()) {
            usuario.get().setUsuario(credenciales.getUsername());
            usuario.get().setClave(jasyptService.EncryptValor(
                    credenciales.getPassword()));
            return usuarioRepository.save(usuario.get());
        } else {
            return null;
        }
    }

    public Optional<Usuario> LoginByUsuarioAndClave(
            UsuarioCredentialsDto credenciales) {
        Optional<Usuario> optionalUsuario
                = usuarioRepository.findByUsuario(credenciales.getUsername());
        if (optionalUsuario.isPresent()) {
            if (optionalUsuario.get().isActivo()) {
                String decif = jasyptService.DecryptValor(optionalUsuario.get()
                        .getClave());
                if (decif.equals(credenciales.getPassword())) {
                    return optionalUsuario;
                } else {
                    return optionalUsuario = Optional.empty();
                }
            } else {
                return optionalUsuario = Optional.empty();
            }
        } else {
            return optionalUsuario;
        }
    }

    public String ChangeUsuarioStatus(int id, boolean activo) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuario.get().setActivo(activo);
            usuarioRepository.save(usuario.get());
            return "Estado de cuenta de usuario con id: " + String.valueOf(id)
                    + " cambiada a: " + String.valueOf(activo);
        } else {
            return "Cuenta de usuario inexistente con id: "
                    + String.valueOf(id);
        }
    }

    public String SendEmailByRecoveryCredentials(String correo)
            throws SendFailedException {
        Optional<DatosUsuario> datosUsuario
                = datosUsuarioRepository.findByCorreo(correo);
        if (datosUsuario.isPresent()) {
            String user = datosUsuario.get().getUsuario().getUsuario();
            String password = jasyptService.DecryptValor(datosUsuario.get()
                    .getUsuario().getClave());
            String body = "TOLAND APP\n\nHa solicitado la recuperación de sus "
                    + "credenciales de usuario. "
                    + "Los cuales son los siguientes:\n\nUsuario: "
                    + user + "\nContraseña: " + password + "\n\nPor su "
                    + "seguridad, recomendamos cambie sus credenciales de "
                    + "usuario directamente en la aplicación.";
            Map<String, String> data = emailService.SendEmail(correo, body);
            if (data.get("Status").equals("ok")) {
                return "El correo de recuperación se ha enviado a " + correo;
            } else {
                return data.get("Response");
            }
        } else {
            return "No existe un usuario con el correo "
                    + correo + " en el sistema";
        }
    }

    private boolean ExistsUsuarioSave(String username, String correo,
            String telefono) {
        if (!usuarioRepository.findByUsuario(username).isPresent()) {
            if (!datosUsuarioRepository.findByCorreo(correo).isPresent()) {
                if (!datosUsuarioRepository.findByTelefono(telefono)
                        .isPresent()) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public Optional<DatosUsuario> GetByCorreo(String correo) {
        return datosUsuarioRepository.findByCorreo(correo);
    }

}
