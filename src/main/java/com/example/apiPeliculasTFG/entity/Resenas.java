package com.example.apiPeliculasTFG.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Resenas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String comentario;
    private double numeroEstrellas;
    
    public Resenas(String id, String comentario, double numeroEstrellas) {
        this.id = id;
        this.comentario = comentario;
        this.numeroEstrellas = numeroEstrellas;
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
}