package com.app.musica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.musica.feignclients.ArtistsFeignClient;

@RestController
@RequestMapping(value = "/musicapp")
public class HomeController {


	@GetMapping(value = "/home")
	public ResponseEntity<?> home(){
		
		return artistsFeignClient.explorarCanciones();
	}
	
	
	
	@GetMapping(value = "/artistas")
	public ResponseEntity<?> todosLosArtistas(){
		
		return artistsFeignClient.listarArtistas();
	}
	
	
	@GetMapping(value = "/artista/{idArtista}")
	public ResponseEntity<?> listarCancionesByArtista(@PathVariable(name = "idArtista")Long idArtista){
		
		return artistsFeignClient.cancionesPorArtista(idArtista);
	}
	
	
	@Autowired
	private ArtistsFeignClient artistsFeignClient;
}

