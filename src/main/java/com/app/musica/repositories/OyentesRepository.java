package com.app.musica.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.musica.models.Oyente;


/**
 * Repositorio DAO de la entidad oyentes
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 3:36:20 p. m.
 */
public interface OyentesRepository extends JpaRepository<Oyente,Long> {

	Optional<Oyente> findByEmail(String email);
	boolean existsByEmail(String email);
}
