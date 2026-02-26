package com.example.apiPeliculasTFG.repository;

import com.example.apiPeliculasTFG.entity.Peliculas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculasRepository extends MongoRepository<Peliculas, String> {
	
}