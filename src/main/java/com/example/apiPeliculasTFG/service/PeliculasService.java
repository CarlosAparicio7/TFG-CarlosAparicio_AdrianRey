package com.example.apiPeliculasTFG.service;

import com.example.apiPeliculasTFG.entity.Peliculas;
import com.example.apiPeliculasTFG.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PeliculasService {

    @Autowired
    private PeliculasRepository peliculasRepository;

    public Peliculas guardarPelicula(String nombre, String descripcion, double valoracion, MultipartFile archivo) throws IOException {
        Peliculas peli = new Peliculas();
        peli.setNombre(nombre);
        peli.setDescripcion(descripcion);
        peli.setValoracion(valoracion);
        peli.setArchivoVideo(archivo.getBytes());
        return peliculasRepository.save(peli);
    }

    public List<Map<String, Object>> obtenerTodas() {
        return peliculasRepository.findAll().stream().map(peli -> {
            Map<String, Object> dto = new HashMap<>();
            dto.put("id", peli.getId());
            dto.put("nombre", peli.getNombre());
            dto.put("descripcion", peli.getDescripcion());
            dto.put("valoracion", peli.getValoracion());
            dto.put("urlVideo", "http://localhost:8080/peliculas/verPelicula/" + peli.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    public Peliculas buscarPorId(Long id) {
        return peliculasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película con ID " + id + " no encontrada"));
    }

    public Peliculas actualizarPelicula(Long id, String nombre, String descripcion, double valoracion, MultipartFile archivo) throws IOException {
        Peliculas peli = buscarPorId(id);
        
        peli.setNombre(nombre);
        peli.setDescripcion(descripcion);
        peli.setValoracion(valoracion);

        if (archivo != null && !archivo.isEmpty()) {
            peli.setArchivoVideo(archivo.getBytes());
        }

        return peliculasRepository.save(peli);
    }

    public void borrarPelicula(Long id) {
        if (!peliculasRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Película no encontrada");
        }
        peliculasRepository.deleteById(id);
    }
}