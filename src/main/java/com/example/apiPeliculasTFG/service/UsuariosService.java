package com.example.apiPeliculasTFG.service;

import java.util.List;

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
}