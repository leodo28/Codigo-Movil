package com.santiago.canchas.Models;

public class Cancha {
    int id;
    String nombre;
    String direccion;
    Float precio_hora;
    String foto;

    public Cancha(int id, String nombre, String direccion, Float precio_hora, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.precio_hora = precio_hora;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Float getPrecio_hora() {
        return precio_hora;
    }

    public void setPrecio_hora(Float precio_hora) {
        this.precio_hora = precio_hora;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
