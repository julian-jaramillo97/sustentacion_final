package com.domain.app.model;

import jakarta.persistence.*;

@Entity
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String marca;
    private String modelo;
    private String año;
    private String color;
    private Float precio;
    private String tipo;
    private String concesionaria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConcesionaria() {
        return concesionaria;
    }

    public void setConcesionaria(String concesionaria) {
        this.concesionaria = concesionaria;
    }

    public Auto() {
    }

    public Auto(Long id, String marca, String modelo, String año, String color, Float precio, String tipo,
            String concesionaria) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.color = color;
        this.precio = precio;
        this.tipo = tipo;
        this.concesionaria = concesionaria;
    }

    @Override
    public String toString() {
        return "Autos [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", año=" + año + ", color=" + color
                + ", precio=" + precio + ", tipo=" + tipo + ", concesionaria=" + concesionaria + "]";
    }

}
