package com.example.apiPeliculasTFG.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document(collection = "peliculas")
public class Peliculas {
    @Id
    private String id;
    
    private String nombre;
    private String descripcion;
    private double valoracion;
    
    private List<Resenas> resena;

    @Field("video_mp4")
    private byte[] pelicula;

    public Peliculas(String id, String nombre, String descripcion, double valoracion, List<Resenas> resena,
            byte[] pelicula) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.resena = resena;
        this.pelicula = pelicula;
    }

    public Peliculas() {
        super();
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public List<Resenas> getResena() {
        return resena;
    }

    public void setResena(List<Resenas> resena) {
        this.resena = resena;
    }

    public byte[] getPelicula() {
        return pelicula;
    }

    public void setPelicula(byte[] pelicula) {
        this.pelicula = pelicula;
    }
}