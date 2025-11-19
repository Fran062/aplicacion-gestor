package edu.gestor.aplicacion_gestor.controladores;

import edu.gestor.aplicacion_gestor.entity.Proyecto;
import edu.gestor.aplicacion_gestor.servicios.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoControlador {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public List<Proyecto> obtenerTodosLosProyectos() {
        return proyectoService.buscarTodosLosProyectos();
    }

    @PostMapping
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        return proyectoService.save(proyecto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
}