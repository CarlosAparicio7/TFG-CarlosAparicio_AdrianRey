package com.example.apiPeliculasTFG.controllers;

import com.example.apiPeliculasTFG.entity.Peliculas;
import com.example.apiPeliculasTFG.entity.PeliculaDTO;
import com.example.apiPeliculasTFG.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculasController {

    @Autowired
    private PeliculasService peliculasService;

    @PostMapping("/subirPelicula")
    public ResponseEntity<Peliculas> subirPelicula(
            @RequestParam("nombre") String nombre,
            @RequestParam("portada") String portada,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("valoracion") double valoracion,
            @RequestParam("archivo") MultipartFile archivo) {
        try {
            Peliculas nueva = peliculasService.guardarPelicula(nombre, portada, descripcion, valoracion, archivo);
            return ResponseEntity.ok(nueva);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/listarPeliculas")
    public List<PeliculaDTO> listarTodas() {
        return peliculasService.obtenerTodasDTO();
    }

    @GetMapping("/verPelicula/{id}")
    public ResponseEntity<Resource> verVideo(@PathVariable String id) {
        Peliculas peli = peliculasService.buscarPorId(id);
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + peli.getNombre() + ".mp4\"")
                .body(new ByteArrayResource(peli.getArchivoVideo()));
    }

    @PutMapping("/editarPelicula/{id}")
    public ResponseEntity<Peliculas> editarPelicula(
            @PathVariable String id,
            @RequestParam("nombre") String nombre,
            @RequestParam("portada") String portada,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("valoracion") double valoracion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo) {
        try {
            Peliculas editada = peliculasService.actualizarPelicula(id, nombre, portada, descripcion, valoracion, archivo);
            return ResponseEntity.ok(editada);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/eliminarPelicula/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable String id) {
        peliculasService.borrarPelicula(id);
        return ResponseEntity.noContent().build();
    }
}