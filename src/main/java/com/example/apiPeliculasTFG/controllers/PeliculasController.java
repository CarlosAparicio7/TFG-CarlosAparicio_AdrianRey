package com.example.apiPeliculasTFG.controllers;

import com.example.apiPeliculasTFG.entity.Peliculas;
import com.example.apiPeliculasTFG.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculasController {

    @Autowired
    private PeliculasRepository peliculasRepository;

    @PostMapping("/subir")
    public String subirPelicula(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("valoracion") double valoracion,
            @RequestParam("archivo") MultipartFile video) throws IOException {
        
        if (video.getSize() > 16 * 1024 * 1024) {
            return "Error: El video excede los 16MB permitidos por MongoDB.";
        }

        Peliculas peli = new Peliculas();
        peli.setNombre(nombre);
        peli.setDescripcion(descripcion);
        peli.setValoracion(valoracion);
        
        if (video != null && !video.isEmpty()) {
            peli.setPelicula(video.getBytes());
        }

        peliculasRepository.save(peli);
        return "Película guardada con éxito.";
    }

    @GetMapping("/listar")
    public List<Peliculas> listarTodas() {
        return peliculasRepository.findAll();
    }
}