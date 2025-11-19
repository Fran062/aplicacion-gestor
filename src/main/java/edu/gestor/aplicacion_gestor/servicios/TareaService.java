package edu.gestor.aplicacion_gestor.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gestor.aplicacion_gestor.repositorios.TareasRepositorio;
import edu.gestor.aplicacion_gestor.entity.Tarea;

@Service
public class TareaService {
    @Autowired
    private TareasRepositorio tareaRepositorio;

    public List<Tarea> findAll() {
        return tareaRepositorio.findAll();
    }

    public Tarea save(Tarea tarea) {
        return tareaRepositorio.save(tarea);
    }

    public void eliminarTarea(Long id) {
        tareaRepositorio.deleteById(id);
    }
}
