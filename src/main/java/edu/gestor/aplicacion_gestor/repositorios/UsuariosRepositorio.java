package edu.gestor.aplicacion_gestor.repositorios; // Ajusta el paquete

import edu.gestor.aplicacion_gestor.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuario, Long> {
}