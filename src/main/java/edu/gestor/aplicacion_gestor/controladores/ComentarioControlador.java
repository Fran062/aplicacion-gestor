package edu.gestor.aplicacion_gestor.controladores;

import edu.gestor.aplicacion_gestor.entity.Comentario;
import edu.gestor.aplicacion_gestor.entity.Usuario;
import edu.gestor.aplicacion_gestor.servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioControlador {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public List<Comentario> obtenerTodos() {
        return comentarioService.findAll();
    }
    
    @PostMapping
    public Comentario crearComentario(@RequestBody Comentario comentario) {
        return comentarioService.save(comentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
            if (usuarioActualizado.getContrasena() != null) {
                usuario.setContrasena(usuarioActualizado.getContrasena());
            }
            return Optional.of(usuarioRepositorio.save(usuario));
        } else {
            return Optional.empty();
        }
    }
}