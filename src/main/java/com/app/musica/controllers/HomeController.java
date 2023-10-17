package com.app.musica.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.musica.feignclients.ArtistsFeignClient;
/**
 * Controlador de las peticiones principales de la aplicacion, 
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 3:25:14 p. m.
 */
@RestController
@RequestMapping(value = "/musicapp")
public class HomeController {


	/**
	 * Utiliza un ArtistsFeignClient para obtener todas las canciones.
	 * 
	 * @return todas las canciones de la app.
	 */
	@GetMapping(value = "/home")
	public ResponseEntity<?> home(){
		
		return artistsFeignClient.explorarCanciones();
	}
	
	
	/**
	 * Utiliza un ArtistsFeignClient para obtener todos los artistas.
	 *
	 * 
	 * @return todos los artistas de la app.
	 */
	@GetMapping(value = "/artistas")
	public ResponseEntity<?> todosLosArtistas(){
		
		return artistsFeignClient.listarArtistas();
	}
	
	
	/**
	 * Utiliza un ArtistsFeignClient para obtener todas las canciones en la app.
	 * 
	 * @param idArtista id del artista que se desea ver sus canciones.
	 * @return lista de canciones del artista.
	 */
	@GetMapping(value = "/artista/{idArtista}")
	public ResponseEntity<?> listarCancionesByArtista(@PathVariable(name = "idArtista")Long idArtista){
		
		return artistsFeignClient.cancionesPorArtista(idArtista);
	}
	
	
	/**
	 * Utiliza un ArtistsFeignClient para buscar una cancion.
	 * Este endpoint esta protegido, solo es permitido para usuarios autenticados.
	 * 
	 * @param idCancion es el id de la cancion
	 * @return la cancion con el id.
	 */
	@GetMapping(value = "/songs/{idCancion}")
	public ResponseEntity<Object> searchCancion(@PathVariable(name = "idCancion") Long idCancion){
	
		return artistsFeignClient.buscarCancion(idCancion);
	}
	
	
	@Autowired
	private ArtistsFeignClient artistsFeignClient;
}

