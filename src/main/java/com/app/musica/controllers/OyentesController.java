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
/**
 * Controlador de la peticiones autorizadas solo para oyentes.
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 3:13:48 p. m.
 */
@RestController
@RequestMapping(value = "/musicapp/favorites")
public class OyentesController {
	
	
	/**
	 * utiliza el OyentsFeignClient para obtener el listado de canciones favoritas del usuario autenticado.
	 * 
	 * @param authentication contiene los datos del usuario autenticado en la peticion. 
	 * Lo utiliza para obtener el id del oyente en la base de datos.
	 * 
	 * @return devuelve la respuesta de la  API oyentes a traves del OyentsFeignClient.
	 */
	@GetMapping(value = "/")
	public ResponseEntity<?> getFavorites(Authentication authentication){
		
		Oyente oyente= oyentesRepository.findByEmail(authentication.getName()).get();
		
		return oyentsFeignClient.listFavoritas(oyente.getId());
	}
	
	
	/**
	 * Utiliza el OyentsFeignClient para agregar una cacnicon a las favoritas del oyente autenticado.
	 * 
	 * @param idCancion id de la cancion.
	 * @param authentication contiene los datos del usuario autenticado en la peticion. 
	 * Lo utiliza para obtener el id del oyente en la base de datos.
	 * 
	 * @return devuelve la respuesta de la  API oyentes a traves del OyentsFeignClient.
	 */
	@PostMapping(value = "/save/{idcancion}")
	public ResponseEntity<String> agregarAfavoritas(@PathVariable(name = "idcancion")Long idCancion,
			Authentication authentication){
		
		Oyente oyente= oyentesRepository.findByEmail(authentication.getName()).get();
		
		
		return oyentsFeignClient.addFavorita(oyente.getId(), idCancion);
	}
	
	
	
	/**
	 * Utiliza el OyentsFeignClient para obtener una cancio favorita del usuario autenticado.
	 * 
	 * @param idFavorita id de la cancion favorita en el listado, No es el id de la cancion sino de la favorita.
	 * @param authentication contiene los datos del usuario autenticado en la peticion. 
	 * Lo utiliza para obtener el id del oyente en la base de datos.
	 * 
	 * @return devuelve la respuesta de la  API oyentes a traves del OyentsFeignClient.
	 */
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
	
	
	/**
	 * Utiliza el OyentsFeignClient para eliminar una cancion del listado de favoritas del oyente autenticado.
	 * 
	 * @param idFavorita id de la cancion favorita.
	 * @param authentication contiene los datos del usuario autenticado en la peticion. 
	 * Lo utiliza para obtener el id del oyente en la base de datos.
	 * 
	 * @return devuelve la respuesta de la  API oyentes a traves del OyentsFeignClient.
	 */
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
