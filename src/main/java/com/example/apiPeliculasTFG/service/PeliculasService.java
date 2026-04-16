package com.example.apiPeliculasTFG.service;

import com.example.apiPeliculasTFG.entity.Peliculas;
import com.example.apiPeliculasTFG.entity.PeliculaDTO;
import com.example.apiPeliculasTFG.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

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

    public List<PeliculaDTO> obtenerTodasDTO() {
        return peliculasRepository.findAllDTO();
    }

    public Peliculas buscarPorId(String id) {
        return peliculasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));
    }

    public Peliculas actualizarPelicula(String id, String nombre, String descripcion, double valoracion, MultipartFile archivo) throws IOException {
        Peliculas peli = buscarPorId(id);
        peli.setNombre(nombre);
        peli.setDescripcion(descripcion);
        peli.setValoracion(valoracion);
        if (archivo != null && !archivo.isEmpty()) {
            peli.setArchivoVideo(archivo.getBytes());
        }
        return peliculasRepository.save(peli);
    }

    public void borrarPelicula(String id) {
        if (!peliculasRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Película no encontrada");
        }
        peliculasRepository.deleteById(id);
    }
}