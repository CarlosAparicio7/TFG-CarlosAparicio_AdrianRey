package com.example.apiPeliculasTFG.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiPeliculasTFG.entity.Resenas;
import com.example.apiPeliculasTFG.repository.ResenasRepository;

@Service
public class ResenasService {

	@Autowired
	private ResenasRepository resenasRepository;
	
	public List<Resenas> obtenerTodos() {
		return resenasRepository.findAll();
	}
}
