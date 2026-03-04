package com.example.apiPeliculasTFG.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiPeliculasTFG.entity.Usuarios;
import com.example.apiPeliculasTFG.service.UsuariosService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/listar")
    public List<Usuarios> listarUsuarios() {
        return usuariosService.obtenerTodos();
    }
    
    @PostMapping("/crear")
    public Usuarios crearUsuario(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("avatarIcon") String avatarIcon,
            @RequestParam("rol") String rol) {
        
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setAvatarIcon(avatarIcon);
        nuevoUsuario.setRol(rol);
        
        return usuariosService.crearUsuario(nuevoUsuario);
    }
}
