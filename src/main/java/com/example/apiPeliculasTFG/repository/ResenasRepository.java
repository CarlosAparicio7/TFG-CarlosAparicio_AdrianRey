package com.example.apiPeliculasTFG.repository;

import org.springframework.stereotype.Repository;
import com.example.apiPeliculasTFG.entity.Resenas;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ResenasRepository extends JpaRepository<Resenas, String> {

    boolean existsByUsuarioIdAndPeliculaId(String usuarioId, String peliculaId);

}