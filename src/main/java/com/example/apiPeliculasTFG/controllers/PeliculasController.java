package com.example.apiPeliculasTFG.controllers;

import com.example.apiPeliculasTFG.entity.Peliculas;
import com.example.apiPeliculasTFG.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculasController {

    @Autowired
    private PeliculasRepository peliculasRepository;

    // 1. SUBIR PELÍCULA (POST)
    @PostMapping("/subir")
    public Peliculas subirPelicula(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("valoracion") double valoracion,
            @RequestParam("archivo") MultipartFile archivo) {

        try {
            System.out.println("Recibiendo: " + archivo.getOriginalFilename() + " - Tamaño: " + archivo.getSize());

            Peliculas peli = new Peliculas();
            peli.setNombre(nombre);
            peli.setDescripcion(descripcion);
            peli.setValoracion(valoracion);
            peli.setArchivoVideo(archivo.getBytes()); 

            Peliculas guardada = peliculasRepository.save(peli);
            System.out.println("¡Peli guardada correctamente con ID: " + guardada.getId());
            return guardada;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Fallo al leer los bytes del video: " + e.getMessage());
        }
    }

    // 2. LISTAR TODAS (GET) - Versión rápida sin descargar los bytes del vídeo
    @GetMapping("/listar")
    public List<Map<String, Object>> listarTodas() {
        return peliculasRepository.findAll().stream().map(peli -> {
            Map<String, Object> dto = new HashMap<>();
            dto.put("id", peli.getId());
            dto.put("nombre", peli.getNombre());
            dto.put("descripcion", peli.getDescripcion());
            dto.put("valoracion", peli.getValoracion());
            // Generamos la URL para que el Front-end sepa dónde pedir el vídeo
            dto.put("urlVideo", "http://localhost:8080/peliculas/ver/" + peli.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    // 3. VER/REPRODUCIR VÍDEO (GET)
    @GetMapping("/ver/{id}")
    public ResponseEntity<Resource> verVideo(@PathVariable Long id) {
        Peliculas peli = peliculasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + peli.getNombre() + ".mp4\"")
                .body(new ByteArrayResource(peli.getArchivoVideo()));
    }
}