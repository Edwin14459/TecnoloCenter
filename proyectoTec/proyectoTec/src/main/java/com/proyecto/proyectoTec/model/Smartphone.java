package com.proyecto.proyectoTec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Smartphone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String marca;
    private String modelo;
    private String ram; 
    private String almacenamiento;
    private String especificaciones;
    private String precio;
    private String imagenURL;

    public Smartphone() {
        super();
    }


    public Smartphone(Integer id, String marca, String modelo, String ram, String almacenamiento, String especificaciones, String precio, String imagenURL) {
        super();
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.almacenamiento = almacenamiento; 
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


    public String getRam() {
        return ram;
    }


    public void setRam(String ram) {
        this.ram = ram;
    }


    public String getAlmacenamiento() {
        return almacenamiento;
    }


    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
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
