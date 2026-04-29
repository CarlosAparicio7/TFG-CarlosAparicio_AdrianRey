package com.example.apiPeliculasTFG.repository;

import com.example.apiPeliculasTFG.entity.Peliculas;
import com.example.apiPeliculasTFG.entity.PeliculaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PeliculasRepository extends JpaRepository<Peliculas, String> {

    @Query("SELECT new com.example.apiPeliculasTFG.entity.PeliculaDTO(p.id, p.nombre, p.portada, p.descripcion, p.director, p.genero, p.valoracion) FROM Peliculas p")
    List<PeliculaDTO> findAllDTO();
}