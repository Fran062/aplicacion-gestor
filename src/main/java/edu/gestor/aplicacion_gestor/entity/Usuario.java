package edu.gestor.aplicacion_gestor.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario", length = 100)
    private String nombreUsuario;

    @Column(name = "correo", length = 100, unique = true)
    private String correo;

    @Column(name = "contraseña", length = 255)
    private String contrasena;

    @Column(name = "rol_id")
    private Integer rolId;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Proyecto> proyectos;

    @OneToMany(mappedBy = "asignadaA")
    private Set<Tarea> tareasAsignadas;

    @OneToMany(mappedBy = "usuario")
    private Set<Comentario> comentarios;

}