package edu.gestor.aplicacion_gestor.repositorios;

import edu.gestor.aplicacion_gestor.entity.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}