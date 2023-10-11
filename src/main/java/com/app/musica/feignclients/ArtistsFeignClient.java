package com.app.musica.feignclients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "servicio-artistas",url = "localhost:8004")
public interface ArtistsFeignClient {

	@GetMapping(value = "/canciones/explorar")
	public ResponseEntity<?> explorarCanciones();
	
	
	@GetMapping(value = "/canciones/artistas")
	public ResponseEntity<?> listarArtistas();
	
	
	@GetMapping(value = "/canciones/artista/{idArtista}")
	public ResponseEntity<?> cancionesPorArtista(@PathVariable(name = "idArtista")Long idArtista);
}
