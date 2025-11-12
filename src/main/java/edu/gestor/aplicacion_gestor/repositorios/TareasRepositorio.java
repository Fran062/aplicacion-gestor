package edu.gestor.aplicacion_gestor.repositorios; // Ajusta el paquete

import edu.gestor.aplicacion_gestor.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasRepositorio extends JpaRepository<Tarea, Long> {
}