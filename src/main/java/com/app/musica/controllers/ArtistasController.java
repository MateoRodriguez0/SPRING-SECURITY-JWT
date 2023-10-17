package com.app.musica.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.musica.feignclients.ArtistFeignClient2;
import com.app.musica.models.Artista;
import com.app.musica.models.Cancion;
import com.app.musica.repositories.ArtistasRepository;

import jakarta.validation.Valid;

/**
 * controlador de las peticiones que seran protegidas y solo seran permitidas a la autoridad OYENTE.
 * 
 * @Author Mateo Rodriguez c.
 * 13 oct. 2023 7:42:43 a. m.
 */
@RestController
@RequestMapping(value = "/musicapp")
public class ArtistasController {

	/**
	 *  Utiliza un artistFeignClient2 para publicar una cancion por el artista autenticado.
	 * 
	 * @param cancion objeto que representa la cancion que sera publicada en la app.
	 * @param bindingResult contiene informacion sobre la validacion del requestBody de la peticion.
	 * @param authentication es el objeto que contiene la informacion del usuario authenticado.
	 * @return devuelve una descripcion de la respuesta a la solicitud.
	 */
	@PostMapping(value = "/mysongs/new")
	public ResponseEntity<?> saveSong(@Valid @RequestBody Cancion cancion, BindingResult bindingResult, 
			Authentication authentication){
		
		if(bindingResult.hasErrors()) {
			
			return ResponseEntity.badRequest().body(bindingResult.getAllErrors()
													.stream()
													.map(o ->o.getDefaultMessage())
													.collect(Collectors.toList()));
		}
		
		else {
			Artista artista = artistasRepository.findByEmail(authentication.getName()).get();
			
			return artistFeignClient2.publicarCancion(cancion, artista.getId());
		}
		
		
	}
	
	/**
	 *  Utiliza un artistFeignClient2 para actualizar una cancion.
	 * 
	 * @param cancion objeto que representa la cancion que se quiere actualizar.
	 * @param bindingResult contiene informacion sobre la validacion del requestBody de la peticion.
	 * @param authentication es el objeto que contiene la informacion del usuario authenticado.
	 * @return si la solicitud es enviada correctamente devuelve la cancion actualizada. 
	 */
	@PutMapping(value = "/mysongs/update")
	public ResponseEntity<?> updateSong(@Valid @RequestBody Cancion cancion, BindingResult bindingResult,
				Authentication authentication){
			
		Artista artista = artistasRepository.findByEmail(authentication.getName()).get();
			
		if(cancion.getId()==null) {
			return ResponseEntity.badRequest().body("hace falta el campo id");
				
		}
		if(bindingResult.hasErrors()) {
			
			return ResponseEntity.badRequest().body(bindingResult.getAllErrors()
														.stream()
														.map(o ->o.getDefaultMessage())
														.collect(Collectors.toList()));
		}
			
		ResponseEntity<?> cancionActualizada = artistFeignClient2.updateCancion(artista.getId(), cancion);
			
	
		return cancionActualizada;
			
	}
	
	/**
	 * Utiliza un artistFeignClient2 para eliminar una cancion publicada por el artista autenticado.
	 * 
	 * @param idCancion id de la cancion que se desea eliminar de la app.
	 * @param authentication es el objeto que contiene la informacion del usuario authenticado.
	 * @return devuelve un responseEntity que contiene una descripcion del resutlado de la peticion.
	 */
	@DeleteMapping(value = "/mysongs/delete/{idCancion}")
	public ResponseEntity<?> deleteSong(@PathVariable(name = "idCancion") Long idCancion, Authentication authentication){
		
		Artista artista = artistasRepository.findByEmail(authentication.getName()).get();
		
		return artistFeignClient2.deleteCancion(artista.getId(), idCancion);
		
	}
	
	
	@Autowired 
	private ArtistasRepository artistasRepository;
	
	@Autowired
	private ArtistFeignClient2 artistFeignClient2;
}
