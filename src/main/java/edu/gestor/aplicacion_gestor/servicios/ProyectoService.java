package edu.gestor.aplicacion_gestor.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gestor.aplicacion_gestor.repositorios.ProyectosRepositorio;
import edu.gestor.aplicacion_gestor.entity.Proyecto;

@Service
public class ProyectoService {
    @Autowired
    private ProyectosRepositorio proyectoRepositorio;

    public ProyectoService(ProyectosRepositorio proyectoRepositorio) { 
        this.proyectoRepositorio = proyectoRepositorio;
    }

    public List<Proyecto> buscarTodosLosProyectos() {
        return proyectoRepositorio.findAll();
    }

    public Proyecto save(Proyecto proyecto) {
        return proyectoRepositorio.save(proyecto);
    }

    public void eliminarProyecto(Long id) {
        proyectoRepositorio.deleteById(id);
    }
}
