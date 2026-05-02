package com.example.apiPeliculasTFG.service;

import com.example.apiPeliculasTFG.entity.Peliculas;
import com.example.apiPeliculasTFG.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class PeliculasService {

    @Autowired
    private PeliculasRepository peliculasRepository;

    private final String CARPETA_VIDEOS = "C:/tfg_videos/";

    public List<Peliculas> listarTodas() {
        return peliculasRepository.findAll();
    }

    public Peliculas buscarPorId(String id) {
        return peliculasRepository.findById(id).orElse(null);
    }

    public Peliculas guardarPelicula(String nombre, String portada, String descripcion, String director, String genero, double valoracion, MultipartFile archivo) {
        try {
            Path directorio = Paths.get(CARPETA_VIDEOS);
            if (!Files.exists(directorio)) {
                Files.createDirectories(directorio);
            }

            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
            Path rutaCompleta = directorio.resolve(nombreArchivo);

            Files.copy(archivo.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

            Peliculas peli = new Peliculas();
            peli.setNombre(nombre);
            peli.setPortada(portada);
            peli.setDescripcion(descripcion);
            peli.setDirector(director);
            peli.setGenero(genero);
            peli.setValoracion(valoracion);
            peli.setUrlVideo(nombreArchivo);

            return peliculasRepository.save(peli);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el video en el disco: " + e.getMessage());
        }
    }

    public void eliminarPelicula(String id) {
        Peliculas peli = buscarPorId(id);
        
        if (peli != null) {
            if (peli.getUrlVideo() != null) {
                try {
                    Files.deleteIfExists(Paths.get(CARPETA_VIDEOS + peli.getUrlVideo()));
                } catch (IOException e) {
                    System.err.println("No se pudo eliminar el archivo físico: " + e.getMessage());
                }
            }
            peliculasRepository.deleteById(id);
        }
    }
}