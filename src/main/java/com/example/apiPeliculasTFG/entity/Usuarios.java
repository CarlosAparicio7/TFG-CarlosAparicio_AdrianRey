package com.example.apiPeliculasTFG.entity;

import org.springframework.data.annotation.Id;


public class Usuarios {
	
	@Id
	private String id;
	private String nombre;
	private String apellido;
	private String avatarIcon;
	private String rol;
	
	public Usuarios() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuarios(String id, String nombre, String apellido, String avatarIcon, String rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.avatarIcon = avatarIcon;
		this.rol = rol;
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
	
	
	
}
