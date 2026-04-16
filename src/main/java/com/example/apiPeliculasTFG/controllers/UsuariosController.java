package com.example.apiPeliculasTFG.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.apiPeliculasTFG.entity.Usuarios;
import com.example.apiPeliculasTFG.service.UsuariosService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/listarUsuarios")
    public List<Usuarios> listarUsuarios() {
        return usuariosService.obtenerTodos();
    }

    @PostMapping("/crearUsuario")
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

    @PutMapping("/actualizarUsuario/{id}")
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

    @DeleteMapping("/borrarUsuario/{id}")
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
        try {
            Usuarios usuario = usuariosService.login(email, password);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}