package com.example.apiPeliculasTFG.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiPeliculasTFG.entity.Resenas;
import com.example.apiPeliculasTFG.entity.Usuarios;
import com.example.apiPeliculasTFG.service.ResenasService;

@RestController
@RequestMapping("/resenas")
public class ResenasController {

	@Autowired
	private ResenasService resenasService;
	
	@GetMapping("/listar")
	public List<Resenas> listarResenas() {
		return resenasService.obtenerTodos();
	}
	
	@PostMapping("/crear")
    public Resenas crearResena(
            @RequestParam("comentario") String comentario,
            @RequestParam("numeroEstrellas") double numeroEstrellas) {
        
        Resenas nuevoResena = new Resenas();
        
        nuevoResena.setComentario(comentario);
        nuevoResena.setNumeroEstrellas(numeroEstrellas);
        
        return resenasService.crearResena(nuevoResena);
    }
	
	@PutMapping("/actualizar/{id}")
	public Resenas actualizarResena(
            @PathVariable Long id,
            @RequestParam("comentario") String comentario,
            @RequestParam("numeroEstrellas") double numeroEstrellas) {
        
        Resenas resenaData = new Resenas();
        resenaData.setComentario(comentario);
        resenaData.setNumeroEstrellas(numeroEstrellas);
        
        return resenasService.actualizarResena(id, resenaData);
    }
	
	@DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarResena(@PathVariable Long id) {
        try {
            resenasService.eliminarResena(id);
            return ResponseEntity.ok("Resena con ID " + id + " eliminada correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
