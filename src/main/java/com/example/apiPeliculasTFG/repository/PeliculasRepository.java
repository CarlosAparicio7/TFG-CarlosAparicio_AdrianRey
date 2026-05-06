package com.example.apiPeliculasTFG.repository;

import com.example.apiPeliculasTFG.entity.Pelicula;
import com.example.apiPeliculasTFG.entity.ListaPeliculas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, String> {

    @Query("SELECT p FROM Pelicula p")
    List<ListaPeliculas> findAllDTO();
}