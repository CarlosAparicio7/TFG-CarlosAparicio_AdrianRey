package com.example.apiPeliculasTFG.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apiPeliculasTFG.entity.Usuarios;
import com.example.apiPeliculasTFG.repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public Usuarios crearUsuario(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    public Usuarios actualizarUsuario(String id, Usuarios usuarioActualizado) {
        return usuariosRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
            usuarioExistente.setPassword(usuarioActualizado.getPassword());
            usuarioExistente.setAvatarIcon(usuarioActualizado.getAvatarIcon());
            usuarioExistente.setRol(usuarioActualizado.getRol());
            return usuariosRepository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    public Usuarios login(String email, String password) {
        return usuariosRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
    }
}