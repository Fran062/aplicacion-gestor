package edu.gestor.aplicacion_gestor.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import edu.gestor.aplicacion_gestor.repositorios.UsuariosRepositorio;
import edu.gestor.aplicacion_gestor.dto.LoginDTO;
import edu.gestor.aplicacion_gestor.entity.Usuario;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuariosRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder encriptadorContrasena;

    public UsuarioService(UsuariosRepositorio usuarioRepositorio) { 
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public List<Usuario> buscarTodosLosUsuarios() {
        return usuarioRepositorio.findAll();
    }
    
    public Usuario save(Usuario usuario) {
        
        String contrasenaTextoPlano = usuario.getContraseña();
        String contrasenaHasheada = encriptadorContrasena.encode(contrasenaTextoPlano);
        usuario.setContraseña(contrasenaHasheada);

        return usuarioRepositorio.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }

    public Optional<Usuario> actualizarUsuario(Long id, Usuario usuarioActualizado) {
        
        Optional<Usuario> usuarioExistente = usuarioRepositorio.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            if (usuarioActualizado.getNombreUsuario() != null) {
                usuario.setNombreUsuario(usuarioActualizado.getNombreUsuario());
            }
            if (usuarioActualizado.getCorreo() != null) {
                usuario.setCorreo(usuarioActualizado.getCorreo());
            }
            if (usuarioActualizado.getContraseña() != null) {
                usuario.setContraseña(usuarioActualizado.getContraseña());
            }
            return Optional.of(usuarioRepositorio.save(usuario));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id); 
    }

    public Optional<Usuario> obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
        return usuarioRepositorio.findByNombreUsuario(nombreUsuario);
    }

    public Optional<Usuario> autenticarUsuario( LoginDTO datos) {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findByNombreUsuario(datos.getNombreUsuario());

        if (usuarioOpt.isEmpty()) {
            return Optional.empty();
        }

        Usuario usuario = usuarioOpt.get();
        
        boolean passwordMatch = encriptadorContrasena.matches(
            datos.getContraseña(),
            usuario.getContraseña()
        );

        if (passwordMatch) {
            return Optional.of(usuario);
        } else {
            return Optional.empty();
        }
    }
}
