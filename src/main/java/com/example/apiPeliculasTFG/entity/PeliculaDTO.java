package com.example.apiPeliculasTFG.entity;

public class PeliculaDTO {

    private String id;
    private String nombre;
    private String portada;
    private String descripcion;
    private double valoracion;

    public PeliculaDTO() {
    }

    public PeliculaDTO(String id, String nombre, String portada, String descripcion, double valoracion) {
        this.id = id;
        this.nombre = nombre;
        this.portada = portada;
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

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }
}