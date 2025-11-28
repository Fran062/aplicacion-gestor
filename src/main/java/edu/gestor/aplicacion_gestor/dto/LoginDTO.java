package edu.gestor.aplicacion_gestor.dto;

public class LoginDTO {
    private String nombreUsuario;
    private String contrasena;



public LoginDTO() {
}

public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contrasena;
    }    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContraseña(String contraseña) {
        this.contrasena = contraseña;
    }

}
