package com.app.musica.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.musica.feignclients.ArtistsFeignClient;

@RestController
@RequestMapping(value = "/musicapp")
public class HomeController {


	/**
	 * 
	 * @return todas las canciones de la app.
	 */
	@GetMapping(value = "/home")
	public ResponseEntity<?> home(){
		
		System.out.println(encoder.encode("Mateo.123"));
		
		return artistsFeignClient.explorarCanciones();
	}
	
	
	/**
	 * 
	 * @return todos los artistas de la app.
	 */
	@GetMapping(value = "/artistas")
	public ResponseEntity<?> todosLosArtistas(){
		
		return artistsFeignClient.listarArtistas();
	}
	
	
	/**
	 * 
	 * @param idArtista id del artista que se desea ver sus canciones.
	 * @return lista de canciones del artista.
	 */
	@GetMapping(value = "/artista/{idArtista}")
	public ResponseEntity<?> listarCancionesByArtista(@PathVariable(name = "idArtista")Long idArtista){
		
		return artistsFeignClient.cancionesPorArtista(idArtista);
	}
	
	
	@GetMapping(value = "/songs/{idCancion}")
	public ResponseEntity<Object> searchCancion(@PathVariable(name = "idCancion") Long idCancion){
	
		return artistsFeignClient.buscarCancion(idCancion);
	}
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ArtistsFeignClient artistsFeignClient;
}

