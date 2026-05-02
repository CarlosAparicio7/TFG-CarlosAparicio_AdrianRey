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
    public ResponseEntity<Usuarios> crearSesion(@RequestBody Usuarios nuevoUsuario) {
        if (nuevoUsuario.getAvatarIcon() == null) {
            nuevoUsuario.setAvatarIcon("");
        }
        nuevoUsuario.setRol("USER");
        Usuarios creado = usuariosService.crearUsuario(nuevoUsuario);
        return ResponseEntity.ok(creado);
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> iniciarSesion(@RequestBody Usuarios loginData) {
        try {
            Usuarios usuario = usuariosService.login(loginData.getEmail(), loginData.getPassword());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Ha fallado: " + e.getMessage());
        }
    }

    @PutMapping("/actualizarUsuario/{id}")
    public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable String id, @RequestBody Usuarios usuarioData) {
        Usuarios actualizado = usuariosService.actualizarUsuario(id, usuarioData);
        return ResponseEntity.ok(actualizado);
    }
}