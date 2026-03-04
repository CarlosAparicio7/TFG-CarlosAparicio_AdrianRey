package com.example.apiPeliculasTFG.entity;



import jakarta.persistence.*;

@Entity
//@Table(name = "usuarios") No hace falta a no ser que cambie el nombre de la clase, ahi si lo necesito para seguir usando el mismo nombre para la tabla de la base de datos
public class Usuarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    private String apellido;
    private String avatarIcon;
    private String rol;
    
	public Usuarios() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

