package com.example.apiPeliculasTFG.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    private String apellido;
    private String avatarIcon;
    private String rol;

	public Usuarios(Long id) {
		super();
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
	
	
	
}

