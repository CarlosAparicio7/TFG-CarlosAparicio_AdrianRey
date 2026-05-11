package com.example.apiPeliculasTFG.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apiPeliculasTFG.entity.Resenas;
import com.example.apiPeliculasTFG.repository.ResenasRepository;

@Service
public class ResenasService {

    @Autowired
    private ResenasRepository resenasRepository;

    public List<Resenas> obtenerTodos() {
        return resenasRepository.findAll();
    }
    
    public Resenas obtenerPorId(String id) {
        return resenasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resena no encontrada con ID: " + id));
    }

    public Resenas crearResena(Resenas resena) {
        if (resena.getUsuario() == null || resena.getPelicula() == null) {
            throw new RuntimeException("Oye, estamos teniendo problemas técnicos. Por favor, espera pacientemente mientras lo solucionamos.");
        }

        String usuarioId = resena.getUsuario().getId();
        String peliculaId = resena.getPelicula().getId();

        boolean yaExiste = resenasRepository.findAll().stream()
                .anyMatch(r -> r.getUsuario().getId().equals(usuarioId) 
                            && r.getPelicula().getId().equals(peliculaId));

        if (yaExiste) {
            throw new RuntimeException("Ya has publicado una reseña para esta película.");
        }

        return resenasRepository.save(resena);
    }

    public Resenas actualizarResena(String id, Resenas resenaActualizada) {
        return resenasRepository.findById(id).map(resenaExistente -> {
            resenaExistente.setComentario(resenaActualizada.getComentario());
            resenaExistente.setNumeroEstrellas(resenaActualizada.getNumeroEstrellas());
            
            return resenasRepository.save(resenaExistente);
        }).orElseThrow(() -> new RuntimeException("Resena no encontrada con ID: " + id));
    }

    public void eliminarResena(String id) {
        if (resenasRepository.existsById(id)) {
            resenasRepository.deleteById(id);
        } else {
            throw new RuntimeException("Resena no encontrada con ID: " + id);
        }
    }
}