package com.app.musica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.musica.models.Artista;


public interface ArtistasRepository extends JpaRepository<Artista,Long>{

	Optional<Artista> findByEmail(String email);
	boolean existsByEmail(String email);
}
