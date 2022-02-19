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
import com.example.apirestbartolucci.dtos.usuario.UsuarioMessageDto;
import com.example.apirestbartolucci.dtos.usuario.UsuarioSaveDto;
import com.example.apirestbartolucci.dtos.usuario.UsuarioUpdateDto;
import com.example.apirestbartolucci.models.Docente;
import com.example.apirestbartolucci.models.Estudiante;
import com.example.apirestbartolucci.models.Grupo;
import com.example.apirestbartolucci.models.Usuario;
import com.example.apirestbartolucci.repositories.DocenteRepository;
import com.example.apirestbartolucci.repositories.EstudianteRepository;
import com.example.apirestbartolucci.repositories.GrupoRepository;
import com.example.apirestbartolucci.repositories.UsuarioRepository;
import java.util.ArrayList;
import java.util.Date;
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
    EstudianteRepository estudianteRepository;

    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    GrupoRepository grupoRepository;

    @Autowired
    JasyptService jasyptService;

    @Autowired
    EmailService emailService;

    @Autowired
    TwilioService twilioService;

    public UsuarioMessageDto GetAllUsuarios() {
        ArrayList<Usuario> usuarios
                = (ArrayList<Usuario>) usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return new UsuarioMessageDto(false, "No hay registros", null,
                    null, null, null);
        } else {
            return new UsuarioMessageDto(true, "Ok", null, null, null, usuarios);
        }
    }

    public UsuarioMessageDto GetUsuarioById(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return new UsuarioMessageDto(true, "Ok", usuario.get(), null, null, null);
        } else {
            return new UsuarioMessageDto(false, "Id de usuario inexistente", null,
                    null, null, null);
        }
    }

    public UsuarioMessageDto GetAllUsuariosByActivo(boolean activo) {
        ArrayList<Usuario> usuarios
                = (ArrayList<Usuario>) usuarioRepository.findByActivo(activo);
        if (usuarios.isEmpty()) {
            return new UsuarioMessageDto(false, "No hay usuarios con Estado: "
                    + activo, null, null, null, null);
        } else {
            return new UsuarioMessageDto(true, "Ok", null, null, null, usuarios);
        }
    }

    public UsuarioMessageDto SaveUsuario(UsuarioSaveDto usuarioSaveDto) {
        String validation = ExistsUsuarioSave(usuarioSaveDto.getUsuario(),
                usuarioSaveDto.getCorreo(),
                usuarioSaveDto.getTelefono());
        if (!validation.equals("Ok")) {
            return new UsuarioMessageDto(false, validation, null, null, null, null);
        } else {
            Usuario usuario = new Usuario();
            usuario.setUsuario(usuarioSaveDto.getUsuario());
            usuario.setClave(jasyptService.EncryptValor(
                    usuarioSaveDto.getClave()));
            usuario.setActivo(true);
            if (usuarioSaveDto.isIsDocente()) {
                usuario.setTipousuario("DC");
                usuario = usuarioRepository.save(usuario);
                Docente docente = new Docente(0,
                        usuario,
                        usuarioSaveDto.getNombres(),
                        usuarioSaveDto.getApellidos(),
                        usuarioSaveDto.getTelefono(),
                        usuarioSaveDto.getCorreo(),
                        usuarioSaveDto.getFechanacimiento(),
                        null, null);
                docenteRepository.save(docente);
                return new UsuarioMessageDto(true, "Ok", null, usuarioSaveDto, null, null);
            } else {
                usuario.setTipousuario("ES");
                if (ExistsDocenteSelectedSave(
                        usuarioSaveDto.getSelectedDocente().getId(),
                        usuarioSaveDto.getSelectedDocente().getNombres(),
                        usuarioSaveDto.getSelectedDocente().getApellidos())) {
                    usuario = usuarioRepository.save(usuario);
                    Estudiante estudiante = new Estudiante(0,
                            usuario,
                            usuarioSaveDto.getNombres(),
                            usuarioSaveDto.getApellidos(),
                            usuarioSaveDto.getTelefono(),
                            usuarioSaveDto.getCorreo(),
                            usuarioSaveDto.getFechanacimiento(),
                            0, null, null, null);
                    estudiante = estudianteRepository.save(estudiante);
                    Docente docente = docenteRepository.findById(
                            usuarioSaveDto.getSelectedDocente().getId()).get();
                    Grupo grupo = new Grupo(0, docente, estudiante,
                            new Date(), true);
                    grupoRepository.save(grupo);
                    return new UsuarioMessageDto(true, "Ok", null, usuarioSaveDto, null, null);
                } else {
                    return new UsuarioMessageDto(false, "El docente seleccionado"
                            + " no existe", null, null, null, null);
                }
            }
        }
    }

    public UsuarioMessageDto UpdateUsuario(UsuarioUpdateDto usuarioUpdateDto) {
        Optional<Usuario> usuario
                = usuarioRepository.findById(usuarioUpdateDto.getId());
        if (usuario.isPresent()) {
            usuario.get().setUsuario(usuarioUpdateDto.getUsuario());
            usuario.get().setClave(usuarioUpdateDto.getClave());
            usuario.get().setActivo(usuarioUpdateDto.isActivo());
            usuarioRepository.save(usuario.get());
            if (usuarioUpdateDto.isIsDocente()) {
                Optional<Docente> docente = docenteRepository.findById(
                        usuario.get().getDocente().getId());
                if (docente.isPresent()) {
                    docente.get().setNombres(usuarioUpdateDto.getNombres());
                    docente.get().setApellidos(usuarioUpdateDto.getApellidos());
                    docente.get().setTelefono(usuarioUpdateDto.getTelefono());
                    docente.get().setCorreo(usuarioUpdateDto.getCorreo());
                    docente.get().setFechanacimiento(
                            usuarioUpdateDto.getFechanacimiento());
                    docenteRepository.save(docente.get());
                    return new UsuarioMessageDto(true, "Ok",
                            null, null, usuarioUpdateDto, null);
                } else {
                    return new UsuarioMessageDto(false, "Id de docente inexistente",
                            null, null, null, null);
                }
            } else {
                Optional<Estudiante> estudiante = estudianteRepository.findById(
                        usuario.get().getEstudiante().getId());
                if (estudiante.isPresent()) {
                    estudiante.get().setNombres(usuarioUpdateDto.getNombres());
                    estudiante.get().setApellidos(usuarioUpdateDto.getApellidos());
                    estudiante.get().setTelefono(usuarioUpdateDto.getTelefono());
                    estudiante.get().setCorreo(usuarioUpdateDto.getCorreo());
                    estudiante.get().setFechanacimiento(
                            usuarioUpdateDto.getFechanacimiento());
                    estudianteRepository.save(estudiante.get());
                    return new UsuarioMessageDto(true, "Ok",
                            null, null, usuarioUpdateDto, null);
                } else {
                    return new UsuarioMessageDto(false, "Id de estudiante inexistente",
                            null, null, null, null);
                }
            }
        } else {
            return new UsuarioMessageDto(false, "Id de usuario inexistente",
                    null, null, null, null);
        }
    }

    /*public Usuario UpdateCredentials(UsuarioUpdateCredentialsDto credenciales) {
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
    }*/
    public UsuarioMessageDto LoginByUsuarioAndClave(
            UsuarioCredentialsDto credenciales) {
        Optional<Usuario> optionalUsuario
                = usuarioRepository.findByUsuario(credenciales.getUsername());
        if (optionalUsuario.isPresent()) {
            if (optionalUsuario.get().isActivo()) {
                String decif = jasyptService.DecryptValor(optionalUsuario.get()
                        .getClave());
                if (decif.equals(credenciales.getPassword())) {
                    return new UsuarioMessageDto(true, "Ok",
                            optionalUsuario.get(), null, null, null);
                } else {
                    return new UsuarioMessageDto(false, "La clave es incorrecta",
                            null, null, null, null);
                }
            } else {
                return new UsuarioMessageDto(false, "El usuario: "
                        + credenciales.getUsername() + " tiene desactivada la cuenta",
                        null, null, null, null);
            }
        } else {
            return new UsuarioMessageDto(false, "El usuario: "
                    + credenciales.getUsername() + " no existe en el sistema",
                    null, null, null, null);
        }
    }

    public String SendEmailByRecoveryCredentials(String correo)
            throws SendFailedException {
        String user = "";
        String password = "";
        Optional<Docente> docente = docenteRepository.findByCorreo(correo);
        if (docente.isPresent()) {
            user = docente.get().getUsuario().getUsuario();
            password = jasyptService.DecryptValor(docente.get()
                    .getUsuario().getClave());
            String body = "Recovery Account TOLAN\n\nCREDENTIALS\nUser: "
                    + user + "\nPassword: " + password + "\n\nFor your security,"
                    + " we recommend changing your user credentials directly in the app";
            Map<String, String> data = emailService.SendEmailVersion2(correo, body);
            if (data.get("Status").equals("ok")) {
                return "El correo de recuperación se ha enviado a " + correo;
            } else {
                return data.get("Response");
            }
        } else {
            Optional<Estudiante> estudiante
                    = estudianteRepository.findByCorreo(correo);
            if (estudiante.isPresent()) {
                user = estudiante.get().getUsuario().getUsuario();
                password = jasyptService.DecryptValor(estudiante.get()
                        .getUsuario().getClave());
                String body = "Recovery Account TOLAN\n\nCREDENTIALS\nUser: "
                    + user + "\nPassword: " + password + "\n\nFor your security,"
                    + " we recommend changing your user credentials directly in the app";
                Map<String, String> data = emailService.SendEmailVersion2(correo, body);
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
    }

    public String SendPhoneRecoveryCredentials(String telefono) {
        String user = "";
        String password = "";
        Optional<Docente> docente = docenteRepository.findByTelefono(telefono);
        if (docente.isPresent()) {
            user = docente.get().getUsuario().getUsuario();
            password = jasyptService.DecryptValor(docente.get()
                    .getUsuario().getClave());
            String mensaje = "Recovery Account TOLAN\n\nCREDENTIALS\nUser: "
                    + user + "\nPassword: " + password + "\n\nFor your security,"
                    + " we recommend changing your user credentials directly in the app";
            return twilioService.SendSMS(telefono, mensaje);
        } else {
            Optional<Estudiante> estudiante
                    = estudianteRepository.findByTelefono(telefono);
            if (estudiante.isPresent()) {
                user = estudiante.get().getUsuario().getUsuario();
                password = jasyptService.DecryptValor(estudiante.get()
                        .getUsuario().getClave());
                String mensaje = "Recovery Account TOLAN\n\nCREDENTIALS\nUser: "
                        + user + "\nPassword: " + password + "\n\nFor your security,"
                        + " we recommend changing your user credentials directly in the app";
                return twilioService.SendSMS(telefono, mensaje);
            } else {
                return "No existe un usuario con el telefono "
                        + telefono + " en el sistema";
            }
        }
    }

    private String ExistsUsuarioSave(String username, String correo,
            String telefono) {
        if (!usuarioRepository.findByUsuario(username).isPresent()) {
            if (!docenteRepository.findByCorreo(correo).isPresent()) {
                if (!docenteRepository.findByTelefono(telefono)
                        .isPresent()) {
                    if (!estudianteRepository.findByCorreo(correo)
                            .isPresent()) {
                        if (!estudianteRepository.findByTelefono(telefono)
                                .isPresent()) {
                            return "Ok";
                        } else {
                            return "El telefono: " + telefono + "ya esta en uso";
                        }
                    } else {
                        return "El correo: " + correo + "ya esta en uso";
                    }
                } else {
                    return "El telefono: " + telefono + "ya esta en uso";
                }
            } else {
                return "El correo: " + correo + "ya esta en uso";
            }
        } else {
            return "El Usuario: " + username + " ya esta en uso";
        }
    }

    private boolean ExistsDocenteSelectedSave(int id, String nombres,
            String apellidos) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if (docente.isPresent()) {
            if (docenteRepository.findByNombresAndApellidos(
                    nombres, apellidos).isPresent()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
