package com.example.apiPeliculasTFG.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;

@Entity
public class Peliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nombre;
    private String portada;
    private String descripcion;
    private String director;
    private String genero;
    private double valoracion;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "archivo_video", columnDefinition = "bytea")
    @JdbcTypeCode(Types.BINARY)
    private byte[] archivoVideo;

    public Peliculas() {
    }

    public Peliculas(String id, String nombre, String portada, String descripcion, String director, String genero, double valoracion, byte[] archivoVideo) {
        this.id = id;
        this.nombre = nombre;
        this.portada = portada;
        this.descripcion = descripcion;
        this.director = director;
        this.genero = genero;
        this.valoracion = valoracion;
        this.archivoVideo = archivoVideo;
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

    public byte[] getArchivoVideo() {
        return archivoVideo;
    }

    public void setArchivoVideo(byte[] archivoVideo) {
        this.archivoVideo = archivoVideo;
    }
}