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

    public ArrayList<Usuario> GetAllUsuarios() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    public Optional<Usuario> GetUsuarioById(int id) {
        return usuarioRepository.findById(id);
    }

    public ArrayList<Usuario> GetAllUsuariosByActivo(boolean activo) {
        return (ArrayList<Usuario>) usuarioRepository.findByActivo(activo);
    }

    public UsuarioSaveDto SaveUsuario(UsuarioSaveDto usuarioSaveDto) {
        if (ExistsUsuarioSave(usuarioSaveDto.getUsuario(),
                usuarioSaveDto.getCorreo(),
                usuarioSaveDto.getTelefono())) {
            return null;
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
                    return usuarioSaveDto;
                } else {
                    return null;
                }
            }
            return usuarioSaveDto;
        }
    }

    public UsuarioUpdateDto UpdateUsuario(UsuarioUpdateDto usuarioUpdateDto) {
        if (usuarioUpdateDto.isIsDocente()) {
            Optional<Docente> docente
                    = docenteRepository.findById(usuarioUpdateDto.getId());
            if (docente.isPresent()) {
                docente.get().setNombres(usuarioUpdateDto.getNombres());
                docente.get().setApellidos(usuarioUpdateDto.getApellidos());
                docente.get().setTelefono(usuarioUpdateDto.getTelefono());
                docente.get().setCorreo(usuarioUpdateDto.getCorreo());
                docente.get().setFechanacimiento(
                        usuarioUpdateDto.getFechanacimiento());
                docenteRepository.save(docente.get());
                return usuarioUpdateDto;
            } else {
                return null;
            }
        } else {
            Optional<Estudiante> estudiante
                    = estudianteRepository.findById(usuarioUpdateDto.getId());
            if (estudiante.isPresent()) {
                estudiante.get().setNombres(usuarioUpdateDto.getNombres());
                estudiante.get().setApellidos(usuarioUpdateDto.getApellidos());
                estudiante.get().setTelefono(usuarioUpdateDto.getTelefono());
                estudiante.get().setCorreo(usuarioUpdateDto.getCorreo());
                estudiante.get().setFechanacimiento(
                        usuarioUpdateDto.getFechanacimiento());
                estudianteRepository.save(estudiante.get());
                return usuarioUpdateDto;
            } else {
            }
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
            if (usuario.get().getEstudiante() != null) {
                usuarioRepository.save(usuario.get());
                return "Estado de cuenta de usuario con id: "
                        + String.valueOf(id) + " cambiada a: "
                        + String.valueOf(activo);
            } else if (usuario.get().getDocente() != null) {
                usuarioRepository.save(usuario.get());
                return "Estado de cuenta de usuario con id: "
                        + String.valueOf(id) + " cambiada a: "
                        + String.valueOf(activo);
            } else {
                return "Error (raro): Cuenta de usuario existente pero no"
                        + " contiene datos";
            }
        } else {
            return "Cuenta de usuario inexistente con id: "
                    + String.valueOf(id);
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
            Optional<Estudiante> estudiante
                    = estudianteRepository.findByCorreo(correo);
            if (estudiante.isPresent()) {
                user = estudiante.get().getUsuario().getUsuario();
                password = jasyptService.DecryptValor(estudiante.get()
                        .getUsuario().getClave());
                String body = "TOLAND APP\n\nHa solicitado la recuperación "
                        + "de sus credenciales de usuario. "
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

    private boolean ExistsUsuarioSave(String username, String correo,
            String telefono) {
        if (!usuarioRepository.findByUsuario(username).isPresent()) {
            if (!docenteRepository.findByCorreo(correo).isPresent()) {
                if (!docenteRepository.findByTelefono(telefono)
                        .isPresent()) {
                    if (!estudianteRepository.findByCorreo(correo)
                            .isPresent()) {
                        if (!estudianteRepository.findByTelefono(telefono)
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
            } else {
                return true;
            }
        } else {
            return true;
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
