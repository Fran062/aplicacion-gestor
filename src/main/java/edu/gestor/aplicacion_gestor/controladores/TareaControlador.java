package edu.gestor.aplicacion_gestor.controladores;

import edu.gestor.aplicacion_gestor.entity.Tarea;
import edu.gestor.aplicacion_gestor.servicios.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaControlador {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public List<Tarea> obtenerTodos() {
        return tareaService.findAll();
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaService.save(tarea);
    }
}