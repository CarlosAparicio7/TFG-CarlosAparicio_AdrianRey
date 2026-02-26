package com.example.apiPeliculasTFG.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.apiPeliculasTFG.entity.Usuarios;

@Repository
public interface UsuariosRepository extends MongoRepository<Usuarios, String> {

}
