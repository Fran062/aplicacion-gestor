package edu.gestor.aplicacion_gestor.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gestor.aplicacion_gestor.repositorios.ComentariosRepositorio;
import edu.gestor.aplicacion_gestor.entity.Comentario;

@Service
public class ComentarioService {
    @Autowired
    private ComentariosRepositorio comentarioRepositorio;

    public List<Comentario> findAll() {
        return comentarioRepositorio.findAll();
    }

    public Comentario save(Comentario comentario) {
        return comentarioRepositorio.save(comentario);
    }
}
