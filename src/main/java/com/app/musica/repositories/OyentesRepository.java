package com.app.musica.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.musica.models.Oyente;



public interface OyentesRepository extends JpaRepository<Oyente,Long> {

	Optional<Oyente> findByEmail(String email);
	boolean existsByEmail(String email);
}
