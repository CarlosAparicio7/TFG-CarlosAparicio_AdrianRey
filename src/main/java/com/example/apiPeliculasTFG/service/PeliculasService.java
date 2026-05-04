package com.example.apiPeliculasTFG.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.apiPeliculasTFG.entity.Peliculas;
import com.example.apiPeliculasTFG.repository.PeliculasRepository;

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

    public Peliculas guardarPelicula(String nombre, String portada, String descripcion, String director, String genero, double valoracion, MultipartFile archivo, String urlVideo) {
        try {
            Peliculas peli = new Peliculas();
            peli.setNombre(nombre);
            peli.setPortada(portada);
            peli.setDescripcion(descripcion);
            peli.setDirector(director);
            peli.setGenero(genero);
            peli.setValoracion(valoracion);

            if (archivo != null && !archivo.isEmpty()) {
                Path directorio = Paths.get(CARPETA_VIDEOS);
                if (Files.notExists(directorio)) {
                    Files.createDirectories(directorio);
                }

                String originalName = archivo.getOriginalFilename();
                String safeName = (originalName != null ? originalName : "video").replaceAll("[^a-zA-Z0-9.]", "_");
                String nombreArchivo = UUID.randomUUID().toString() + "_" + safeName;
                
                Path rutaCompleta = directorio.resolve(nombreArchivo);
                Files.copy(archivo.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);
                
                peli.setUrlVideo(nombreArchivo);
            } else {
                peli.setUrlVideo(urlVideo);
            }

            return peliculasRepository.save(peli);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el archivo: " + e.getMessage());
        }
    }
    
    public Peliculas actualizarPelicula(String id, String nombre, String portada, String descripcion, String director, String genero, double valoracion, MultipartFile archivo) throws IOException {
        Peliculas peli = peliculasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con id: " + id));

        peli.setNombre(nombre);
        peli.setPortada(portada);
        peli.setDescripcion(descripcion);
        peli.setDirector(director);
        peli.setGenero(genero);     
        peli.setValoracion(valoracion);

        if (archivo != null && !archivo.isEmpty()) {
            if (peli.getUrlVideo() != null) {
                Files.deleteIfExists(Paths.get(CARPETA_VIDEOS + peli.getUrlVideo()));
            }

            String originalName = archivo.getOriginalFilename();
            String safeName = (originalName != null ? originalName : "video").replaceAll("[^a-zA-Z0-9.]", "_");
            String nuevoNombreArchivo = UUID.randomUUID().toString() + "_" + safeName;
            
            Path directorio = Paths.get(CARPETA_VIDEOS);
            if (Files.notExists(directorio)) {
                Files.createDirectories(directorio);
            }

            Path rutaCompleta = directorio.resolve(nuevoNombreArchivo);
            Files.copy(archivo.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

            peli.setUrlVideo(nuevoNombreArchivo);
        }

        return peliculasRepository.save(peli);
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