package com.example.apiPeliculasTFG.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apiPeliculasTFG.entity.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    
    Optional<Usuarios> findByEmail(String email);
    
}