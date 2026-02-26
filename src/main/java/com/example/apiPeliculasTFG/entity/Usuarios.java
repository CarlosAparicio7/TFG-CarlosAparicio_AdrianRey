package com.example.apiPeliculasTFG.entity;

import org.springframework.data.annotation.Id;


public class Usuarios {
	
	@Id
	private String id;
	private String nombre;
	private String apellido;
	private String avatarIcon;
	private String rol;
	
	
}
