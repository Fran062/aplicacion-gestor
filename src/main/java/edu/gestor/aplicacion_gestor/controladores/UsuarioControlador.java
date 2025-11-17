
package edu.gestor.aplicacion_gestor.controladores;
import edu.gestor.aplicacion_gestor.entity.Usuario;
import edu.gestor.aplicacion_gestor.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        System.out.println(usuario);
        return usuarioService.save(usuario);
    }


}