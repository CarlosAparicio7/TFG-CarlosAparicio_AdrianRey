package com.example.apiPeliculasTFG.controllers;

import com.example.apiPeliculasTFG.entity.Pelicula;
import com.example.apiPeliculasTFG.entity.ListaPeliculas;
import com.example.apiPeliculasTFG.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/listarPeliculas")
    public List<ListaPeliculas> listar() {
        return peliculasService.listarSoloPublicadas();
    }

    @PostMapping("/subirPelicula")
    public ResponseEntity<Pelicula> subirPelicula(
            @RequestParam("nombre") String nombre,
            @RequestParam("portada") String portada,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("director") String director,
            @RequestParam("genero") String genero,
            @RequestParam("valoracion") double valoracion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            @RequestParam("urlVideo") String urlVideo) {
        
        Pelicula nuevaPeli = peliculasService.guardarPelicula(nombre, portada, descripcion, director, genero, valoracion, archivo, urlVideo);
        return ResponseEntity.ok(nuevaPeli);
    }
    
    @PutMapping("/editarPelicula/{id}")
    public ResponseEntity<Pelicula> editarPelicula(
            @PathVariable String id,
            @RequestParam("nombre") String nombre,
            @RequestParam("portada") String portada,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("director") String director,
            @RequestParam("genero") String genero,
            @RequestParam("valoracion") double valoracion,
            @RequestParam("urlVideo") String urlVideo,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo) throws IOException {
        
        Pelicula editada = peliculasService.actualizarPelicula(id, nombre, portada, descripcion, director, genero, valoracion, urlVideo, archivo);
        return ResponseEntity.ok(editada);
    }
        
    @GetMapping("/verPelicula/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable String id) {
        Pelicula peli = peliculasService.buscarPorId(id);
        if (peli != null) {
            return ResponseEntity.ok(peli);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        peliculasService.eliminarPelicula(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/borradores")
    public List<ListaPeliculas> listarBorradores() {
        return peliculasService.listarSoloBorradores();
    }

    @PutMapping("/aceptar/{id}")
    public ResponseEntity<Void> aceptar(@PathVariable String id) {
        peliculasService.publicarPelicula(id);
        return ResponseEntity.ok().build();
    }
}