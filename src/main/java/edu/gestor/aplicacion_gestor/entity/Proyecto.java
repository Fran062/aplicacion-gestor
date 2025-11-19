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

    @Column(name = "fechaDeCreacion")
    private LocalDate fechaDeCreacion;

    @Column(name = "creadoPor")
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

    public Proyecto() {
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaDeCreacion = fechaCreacion;
    }

    public Integer getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }
}