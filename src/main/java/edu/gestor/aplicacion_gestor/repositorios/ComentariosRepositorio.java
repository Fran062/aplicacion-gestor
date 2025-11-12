package edu.gestor.aplicacion_gestor.repositorios; // Ajusta el paquete

import edu.gestor.aplicacion_gestor.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosRepositorio extends JpaRepository<Comentario, Long> {
}
