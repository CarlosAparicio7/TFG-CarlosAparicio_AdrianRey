package com.example.apiPeliculasTFG.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pelicula extends ListaPeliculas {

    @Column(columnDefinition = "TEXT")
    private String urlVideo;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Resenas> resenas = new ArrayList<>();

    public Pelicula() {
        super();
    }

    public Pelicula(String id, String nombre, String portada, String descripcion, String director, String genero, double valoracion, String urlVideo) {
        super(id, nombre, portada, descripcion, director, genero, valoracion);
        this.urlVideo = urlVideo;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public List<Resenas> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resenas> resenas) {
        this.resenas = resenas;
    }
}