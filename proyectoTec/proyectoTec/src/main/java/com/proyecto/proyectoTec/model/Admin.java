package com.proyecto.proyectoTec.model;

import jakarta.persistence.*;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String usuario;
    private String contraseña;
    private String imagenURL;

    public Admin() {
        super();
    }

    public Admin(Integer id, String usuario, String contraseña, String imagenURL) {
        super();
        this.id = id;
        this.usuario = usuario;       
        this.contraseña = contraseña;       
        this.imagenURL = imagenURL;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }
}