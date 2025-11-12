package edu.gestor.aplicacion_gestor.controladores;

import edu.gestor.aplicacion_gestor.entity.Comentario;
import edu.gestor.aplicacion_gestor.servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
}