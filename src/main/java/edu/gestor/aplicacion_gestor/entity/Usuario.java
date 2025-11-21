package edu.gestor.aplicacion_gestor.entity;

import java.util.Set;

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

    @Column(name = "nombre", length = 100)
    private String nombreUsuario;

    @Column(name = "correo", length = 100, unique = true)
    private String correo;

    @Column(name = "contrasena", length = 255)
    private String contrasena;

    @Column(name = "rol")
    private Integer rolId;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Proyecto> proyectos;

    @OneToMany(mappedBy = "asignadaA")
    private Set<Tarea> tareasAsignadas;

    @OneToMany(mappedBy = "usuario")
    private Set<Comentario> comentarios;

    public Usuario() {
    }


    public Integer getId_usuario() {
        return idUsuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.idUsuario = id_usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contrasena;
    }

    public void setContraseña(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getRol() {
        return rolId;
    }

    public void setRol(Integer rol) {
        this.rolId = rol;
    }
}