package com.example.apiPeliculasTFG.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.apiPeliculasTFG.entity.Resenas;
import com.example.apiPeliculasTFG.entity.ListaPeliculas;
import com.example.apiPeliculasTFG.entity.Usuarios;
import com.example.apiPeliculasTFG.service.ResenasService;

@RestController
@RequestMapping("/resenas")
@CrossOrigin(origins = "*")
public class ResenasController {

    @Autowired
    private ResenasService resenasService;

    @GetMapping("/listarResenas")
    public List<Resenas> listarResenas() {
        return resenasService.obtenerTodos();
    }

    @PostMapping("/crearResena")
    public Resenas crearResena(
            @RequestParam("comentario") String comentario,
            @RequestParam("numeroEstrellas") double numeroEstrellas,
            @RequestParam("peliculaId") String peliculaId,
            @RequestParam("usuarioId") String usuarioId) {

        Resenas nuevoResena = new Resenas();
        nuevoResena.setComentario(comentario);
        nuevoResena.setNumeroEstrellas(numeroEstrellas);

        ListaPeliculas peli = new ListaPeliculas();
        peli.setId(peliculaId);
        nuevoResena.setPelicula(peli);

        Usuarios user = new Usuarios();
        user.setId(usuarioId);
        nuevoResena.setUsuario(user);

        return resenasService.crearResena(nuevoResena);
    }

    @PutMapping("/actualizarResena/{id}")
    public Resenas actualizarResena(
            @PathVariable String id,
            @RequestParam("comentario") String comentario,
            @RequestParam("numeroEstrellas") double numeroEstrellas) {

        Resenas resenaData = new Resenas();
        resenaData.setComentario(comentario);
        resenaData.setNumeroEstrellas(numeroEstrellas);

        return resenasService.actualizarResena(id, resenaData);
    }

    @DeleteMapping("/borrarResena/{id}")
    public ResponseEntity<String> borrarResena(@PathVariable String id) {
        try {
            resenasService.eliminarResena(id);
            return ResponseEntity.ok("Resena con ID " + id + " eliminada correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}