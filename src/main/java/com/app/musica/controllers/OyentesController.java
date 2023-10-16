package com.app.musica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.musica.feignclients.OyentsFeignClient;
import com.app.musica.models.Oyente;
import com.app.musica.repositories.OyentesRepository;

@RestController
@RequestMapping(value = "/musicapp/favorites")
public class OyentesController {
	
	
	
	@GetMapping(value = "/")
	public ResponseEntity<?> getFavorites(Authentication authentication){
		
		Oyente oyente= oyentesRepository.findByEmail(authentication.getName()).get();
		
		return oyentsFeignClient.listFavoritas(oyente.getId());
	}
	
	
	@PostMapping(value = "/save/{idcancion}")
	public ResponseEntity<String> agregarAfavoritas(@PathVariable(name = "idcancion")Long idCancion,
			Authentication authentication){
		
		Oyente oyente= oyentesRepository.findByEmail(authentication.getName()).get();
		
		
		return oyentsFeignClient.addFavorita(oyente.getId(), idCancion);
	}
	
	
	
	
	@GetMapping(value = "/search/{idfavorita}")
	public ResponseEntity<?> searchFavorita(@PathVariable(name = "idfavorita")Long idFavorita,
			Authentication authentication){
		
		Oyente oyente= oyentesRepository.findByEmail(authentication.getName()).get();
		
		ResponseEntity<?> response=oyentsFeignClient.getfavorita(oyente.getId(), idFavorita);
		if (response.getBody()!=null) {
			return  response;
		}
		else {
			return new ResponseEntity<String>("La cancion no esta en la lista de favoritos",HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	
	@DeleteMapping(value = "/delete/{idfavorita}")
	public ResponseEntity<?> deleteFavorita(@PathVariable(name = "idfavorita")Long idFavorita,
			Authentication authentication){
		
		Oyente oyente= oyentesRepository.findByEmail(authentication.getName()).get();

		ResponseEntity<?> response= oyentsFeignClient.deleteFavorita(oyente.getId(), idFavorita);
		
		return response;
	}
	

	
	
	
	@Autowired
	private OyentsFeignClient oyentsFeignClient;
	
	
	@Autowired 
	private OyentesRepository oyentesRepository;

}
