package com.example.apiPeliculasTFG.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiPeliculasTFG.entity.Resenas;
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
}
