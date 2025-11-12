package edu.gestor.aplicacion_gestor.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Integer idProyecto;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "creado_por")
    private Integer creadoPor;

    @ManyToMany
    @JoinTable(
        name = "Usuarios_has_proyectos",
        joinColumns = @JoinColumn(name = "id_proyecto_has_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_usuario_has_proyecto")
    )
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "proyecto")
    private Set<Tarea> tareas;

}