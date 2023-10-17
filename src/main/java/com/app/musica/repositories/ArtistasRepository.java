package com.app.musica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.musica.models.Artista;

/**
 * Repositorio DAO de la entidad artistas.
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 3:35:39 p. m.
 */
public interface ArtistasRepository extends JpaRepository<Artista,Long>{

	
	Optional<Artista> findByEmail(String email);
	boolean existsByEmail(String email);
}
