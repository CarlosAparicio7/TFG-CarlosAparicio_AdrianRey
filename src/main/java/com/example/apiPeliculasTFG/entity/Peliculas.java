package com.example.apiPeliculasTFG.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;

@Entity
public class Peliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private double valoracion;

    @Column(name = "archivo_video", columnDefinition = "bytea")
    @JdbcTypeCode(Types.BINARY) // Esto obliga a Hibernate a enviar bytes y no un bigint
    private byte[] archivoVideo;

    // --- GETTERS Y SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getValoracion() { return valoracion; }
    public void setValoracion(double valoracion) { this.valoracion = valoracion; }

    public byte[] getArchivoVideo() { return archivoVideo; }
    public void setArchivoVideo(byte[] archivoVideo) { this.archivoVideo = archivoVideo; }
}