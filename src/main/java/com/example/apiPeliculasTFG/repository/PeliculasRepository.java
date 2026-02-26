package com.example.apiPeliculasTFG.repository;

import com.example.apiPeliculasTFG.entity.Peliculas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculasRepository extends JpaRepository<Peliculas, Long> {
	
}