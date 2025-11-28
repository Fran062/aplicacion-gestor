
package edu.gestor.aplicacion_gestor.controladores;

import edu.gestor.aplicacion_gestor.dto.LoginDTO;
import edu.gestor.aplicacion_gestor.entity.Usuario;
import edu.gestor.aplicacion_gestor.servicios.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {

        return usuarioService.buscarTodosLosUsuarios();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        return usuarioService.actualizarUsuario(id, usuarioActualizado)
                .map(usuarioModificado -> new ResponseEntity<>(usuarioModificado, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {

        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/Nombre/{nombreUsuario}")
    public ResponseEntity<Usuario> obtenerUsuarioPorNombreUsuario(@PathVariable String nombreUsuario) {
        
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        
        // Si el usuario existe, devuelve 200 OK; si no, devuelve 404 Not Found.
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/login") 
    public ResponseEntity<?> login(@RequestBody LoginDTO datos) {
        
        Optional<Usuario> usuario = usuarioService.autenticarUsuario(datos);

        if (usuario.isPresent()) {
            // Retorno Exitoso (200 OK)
            return ResponseEntity.ok(usuario.get()); 
        } else {
            // Retorno de Fallo (401 Unauthorized o 403 Forbidden)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos.");
        }
    }

    
}