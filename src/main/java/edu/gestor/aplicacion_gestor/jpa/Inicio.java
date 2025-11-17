package edu.gestor.aplicacion_gestor.jpa; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "edu.gestor.aplicacion_gestor")
@EnableJpaRepositories(basePackages = "edu.gestor.aplicacion_gestor.repositorios")
@EntityScan(basePackages = "edu.gestor.aplicacion_gestor.entity")
public class Inicio {

    public static void main(String[] args) {
        SpringApplication.run(Inicio.class, args);
    }
}