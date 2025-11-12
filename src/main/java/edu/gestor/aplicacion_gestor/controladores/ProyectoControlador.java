package edu.gestor.aplicacion_gestor.controladores;

import edu.gestor.aplicacion_gestor.entity.Proyecto;
import edu.gestor.aplicacion_gestor.servicios.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoControlador {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public List<Proyecto> obtenerTodos() {
        return proyectoService.findAll();
    }

    @PostMapping
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        return proyectoService.save(proyecto);
    }
}