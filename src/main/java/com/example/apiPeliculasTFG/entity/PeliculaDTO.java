package com.example.apiPeliculasTFG.entity;

public class PeliculaDTO {

    private String id;
    private String nombre;
    private String descripcion;
    private double valoracion;

    public PeliculaDTO() {
    }

    public PeliculaDTO(String id, String nombre, String descripcion, double valoracion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
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
}