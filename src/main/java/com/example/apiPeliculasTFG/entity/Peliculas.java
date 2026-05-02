package com.example.apiPeliculasTFG.entity;

import jakarta.persistence.*;

@Entity
public class Peliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String portada;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String director;
    private String genero;
    private double valoracion;
    private String urlVideo;

    public Peliculas() {
    }

    public Peliculas(String id, String nombre, String portada, String descripcion, String director, String genero, double valoracion, String urlVideo) {
        this.id = id;
        this.nombre = nombre;
        this.portada = portada;
        this.descripcion = descripcion;
        this.director = director;
        this.genero = genero;
        this.valoracion = valoracion;
        this.urlVideo = urlVideo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }
}