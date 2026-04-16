package com.example.apiPeliculasTFG.controllers;

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

    @PostMapping("/crearSesion")
    public Usuarios crearSesion(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "avatarIcon", required = false) String avatarIcon) {

        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setAvatarIcon(avatarIcon != null ? avatarIcon : "");
        nuevoUsuario.setRol("USER");

        return usuariosService.crearUsuario(nuevoUsuario);
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity<String> iniciarSesion(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        try {
            usuariosService.login(email, password);
            return ResponseEntity.ok("Ha pasado");
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Ha fallado");
        }
    }

    @PutMapping("/actualizarUsuario/{id}")
    public Usuarios actualizarUsuario(
            @PathVariable String id,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("avatarIcon") String avatarIcon,
            @RequestParam("rol") String rol) {

        Usuarios usuarioData = new Usuarios();
        usuarioData.setNombre(nombre);
        usuarioData.setApellido(apellido);
        usuarioData.setEmail(email);
        usuarioData.setPassword(password);
        usuarioData.setAvatarIcon(avatarIcon);
        usuarioData.setRol(rol);

        return usuariosService.actualizarUsuario(id, usuarioData);
    }
}