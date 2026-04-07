package com.example.apiPeliculasTFG.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiPeliculasTFG.entity.Resenas;
import com.example.apiPeliculasTFG.entity.Usuarios;
import com.example.apiPeliculasTFG.repository.ResenasRepository;

@Service
public class ResenasService {

	@Autowired
	private ResenasRepository resenasRepository;
	
	public List<Resenas> obtenerTodos() {
		return resenasRepository.findAll();
	}
	
	public Resenas crearResena(Resenas resena) {
        return resenasRepository.save(resena);
    }
	
	public Resenas actualizarResena(Long id, Resenas resenaActualizada) {
		return resenasRepository.findById(id).map(resenaExistente -> {
			resenaExistente.setComentario(resenaActualizada.getComentario());
			resenaExistente.setNumeroEstrellas(resenaActualizada.getNumeroEstrellas());
			
			return resenasRepository.save(resenaExistente);
		}).orElseThrow(() -> new RuntimeException("Resena no encontrada con ID: " + id));
		
	}
	
	public void eliminarResena(Long id) {
	    if (resenasRepository.existsById(id)) {
	    	resenasRepository.deleteById(id);
	    } else {
	            throw new RuntimeException("Resena no encontrada con ID: " + id);
	    }
	}
}
