package com.example.apiPeliculasTFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "resenas", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usuario_id", "pelicula_id"})
})
public class Resenas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String comentario;
    private double numeroEstrellas;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"resenas", "password"})
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    @JsonIgnoreProperties("resenas")
    private ListaPeliculas pelicula;
    
    public Resenas(String id, String comentario, double numeroEstrellas, Usuarios usuario, ListaPeliculas pelicula) {
        this.id = id;
        this.comentario = comentario;
        this.numeroEstrellas = numeroEstrellas;
        this.usuario = usuario;
        this.pelicula = pelicula;
    }

    public Resenas() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getNumeroEstrellas() {
        return numeroEstrellas;
    }

    public void setNumeroEstrellas(double numeroEstrellas) {
        this.numeroEstrellas = numeroEstrellas;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public ListaPeliculas getPelicula() {
        return pelicula;
    }

    public void setPelicula(ListaPeliculas pelicula) {
        this.pelicula = pelicula;
    }
}