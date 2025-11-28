package edu.gestor.aplicacion_gestor.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gestor.aplicacion_gestor.repositorios.ProyectosRepositorio;
import edu.gestor.aplicacion_gestor.entity.Proyecto;

@Service
public class ProyectoService {
    @Autowired
    private ProyectosRepositorio proyectosRepositorio;

    public ProyectoService(ProyectosRepositorio proyectosRepositorio) { 
        this.proyectosRepositorio = proyectosRepositorio;
    }

    public List<Proyecto> buscarTodosLosProyectos() {
        return proyectosRepositorio.findAll();
    }
    
    public Proyecto save(Proyecto proyecto) {
        return proyectosRepositorio.save(proyecto);
    }

    public void eliminarProyecto(Long id) {
        proyectosRepositorio.deleteById(id);
    }

    public Optional<Proyecto> actualizarProyecto(Long id, Proyecto proyectoActualizado) {
        
        Optional<Proyecto> proyectoExistente = proyectosRepositorio.findById(id);

        if (proyectoExistente.isPresent()) {
            Proyecto proyecto = proyectoExistente.get();
            
            if (proyectoActualizado.getNombre() != null) {
                proyecto.setNombre(proyectoActualizado.getNombre());
            }
            if (proyectoActualizado.getDescripcion() != null) {
                proyecto.setDescripcion(proyectoActualizado.getDescripcion());
            }
            return Optional.of(proyectosRepositorio.save(proyecto));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Proyecto> obtenerProyectoPorId(Long id) {
        return proyectosRepositorio.findById(id); 
    }
}