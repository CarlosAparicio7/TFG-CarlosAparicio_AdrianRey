package com.example.apiPeliculasTFG.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiPeliculasTFG.entity.Usuarios;
import com.example.apiPeliculasTFG.repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public List<Usuarios> obtenerTodos() {
        return usuariosRepository.findAll();
    }
    
    public Usuarios crearUsuario(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }
    
    public Usuarios actualizarUsuario(Long id, Usuarios usuarioActualizado) {
        return usuariosRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());
            usuarioExistente.setAvatarIcon(usuarioActualizado.getAvatarIcon());
            usuarioExistente.setRol(usuarioActualizado.getRol());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
            usuarioExistente.setPassword(usuarioActualizado.getPassword());
            
            return usuariosRepository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }
    
    public void eliminarUsuario(Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }

    public Usuarios login(String email, String password) {
        Optional<Usuarios> usuario = usuariosRepository.findByEmail(email);
        
        if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
            return usuario.get();
        }
        
        return null;
    }
}