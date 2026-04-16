package com.example.apiPeliculasTFG.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @PutMapping("/actualizar/{id}")
    public Usuarios actualizarUsuario(
            @PathVariable String id,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("avatarIcon") String avatarIcon,
            @RequestParam("rol") String rol) {
        
        Usuarios usuarioData = new Usuarios();
        usuarioData.setNombre(nombre);
        usuarioData.setApellido(apellido);
		usuarioData.setAvatarIcon(avatarIcon);
        usuarioData.setRol(rol);
        
        return usuariosService.actualizarUsuario(id, usuarioData);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable String id) {
        try {
            usuariosService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario con ID " + id + " eliminado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/crearSesion")
    public Usuarios crearSesion(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(password);
        
        nuevoUsuario.setAvatarIcon("");
        nuevoUsuario.setRol("USER");
        
        return usuariosService.crearUsuario(nuevoUsuario);
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> iniciarSesion(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        
        Usuarios usuario = usuariosService.login(email, password);
        
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}