package edu.gestor.aplicacion_gestor.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import edu.gestor.aplicacion_gestor.repositorios.UsuariosRepositorio;
import edu.gestor.aplicacion_gestor.entity.Usuario;

@Service
public class UsuarioService {
    
    private final UsuariosRepositorio usuarioRepositorio;

    public UsuarioService(UsuariosRepositorio usuarioRepositorio) { 
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public List<Usuario> buscarTodosLosUsuarios() {
        return usuarioRepositorio.findAll();
    }
    
    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }
}
