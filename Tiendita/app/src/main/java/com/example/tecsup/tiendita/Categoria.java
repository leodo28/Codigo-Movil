package com.example.tecsup.tiendita;

public class Categoria {
    public Integer id;
    public String descripcion;
    public String imagen_banner;
    public String imagenCuadrada;
    public Integer nivel;

    public Categoria(Integer id, String descripcion, String imagenBanner, String imagenCuadrada, Integer nivel) {
        this.id = id;
        this.descripcion = descripcion;
        this.imagen_banner = imagenBanner;
        this.imagenCuadrada = imagenCuadrada;
        this.nivel = nivel;
    }
}
