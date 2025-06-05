package com.proyecto.proyectoTec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Televisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String marca;
    private String modelo;
    private String tamaño;
    private String especificaciones;
    private String precio;
    private String imagenURL;

    public Televisor() {
        super();
    }

    public Televisor(Integer id, String marca, String modelo, String tamaño, String especificaciones, String precio, String imagenURL) {
        super();
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.tamaño = tamaño;
        this.especificaciones = especificaciones;
        this.precio = precio;
        this.imagenURL = imagenURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
        public String getImagenURL() {
        return imagenURL;
    }


    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }
}
