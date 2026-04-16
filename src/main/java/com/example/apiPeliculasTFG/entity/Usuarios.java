package com.example.apiPeliculasTFG.entity;

import jakarta.persistence.*;

@Entity
public class Usuarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private String id;
    
    @Column(nullable = false)
    private String nombre;
    
    private String apellido;
    private String avatarIcon;
    private String rol;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    public Usuarios(String id, String nombre, String apellido, String avatarIcon, String rol, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.avatarIcon = avatarIcon;
        this.rol = rol;
        this.email = email;
        this.password = password;
    }

    public Usuarios() {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAvatarIcon() {
        return avatarIcon;
    }

    public void setAvatarIcon(String avatarIcon) {
        this.avatarIcon = avatarIcon;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
