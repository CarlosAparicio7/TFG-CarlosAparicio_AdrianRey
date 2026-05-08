package com.example.apiPeliculasTFG.controllers;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> crearResena(
            @RequestParam("comentario") String comentario,
            @RequestParam("numeroEstrellas") double numeroEstrellas,
            @RequestParam("peliculaId") String peliculaId,
            @RequestParam("usuarioId") String usuarioId) {
        try {
            Resenas nuevoResena = new Resenas();
            nuevoResena.setComentario(comentario);
            nuevoResena.setNumeroEstrellas(numeroEstrellas);

            ListaPeliculas peli = new ListaPeliculas();
            peli.setId(peliculaId);
            nuevoResena.setPelicula(peli);

            Usuarios user = new Usuarios();
            user.setId(usuarioId);
            nuevoResena.setUsuario(user);

            return ResponseEntity.ok(resenasService.crearResena(nuevoResena));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("detalle", e.getMessage()));
        }
    }

    @PutMapping("/actualizarResena/{id}")
    public ResponseEntity<?> actualizarResena(
            @PathVariable String id,
            @RequestParam("comentario") String comentario,
            @RequestParam("numeroEstrellas") double numeroEstrellas) {
        try {
            Resenas resenaData = new Resenas();
            resenaData.setComentario(comentario);
            resenaData.setNumeroEstrellas(numeroEstrellas);
            return ResponseEntity.ok(resenasService.actualizarResena(id, resenaData));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("detalle", e.getMessage()));
        }
    }

    @DeleteMapping("/borrarResena/{id}")
    public ResponseEntity<?> borrarResena(@PathVariable String id) {
        try {
            resenasService.eliminarResena(id);
            return ResponseEntity.ok(Map.of("mensaje", "Resena eliminada correctamente."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("detalle", e.getMessage()));
        }
    }
}