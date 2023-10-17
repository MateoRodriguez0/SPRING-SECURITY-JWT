package com.app.musica.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Este es un cliente feign que se comunica con el microservicio servicio-clientes 
 * que se encuentra disponible en el hots localhost utilizando el puerto 8003.
 * 
 * @Author Mateo Rodriguez c.
 * 12 oct. 2023 4:33:06 p. m.
 */

@FeignClient(name = "servicio-clientes",url = "localhost:8003")
public interface OyentsFeignClient {
	
	
	/**
	 * Busca el listado de canciones favoritas de el usuario asociado a la variable del path {id}.
	 * utiliza el metodo oyenteServices.getFavoritas(id) de la clase de servicio para buscar el oyente en la base de datos,
	 * y devuelve la lista de favoritas del usuario.   
	 * 
	 * @param id es el id del usuario oyente.
	 * @return ResponseEntity con la lista de Favoritas en el body.
	 */
	@GetMapping(value = "/favoritas/listar/{id}")
	public ResponseEntity<?> listFavoritas (@PathVariable(name = "id") Long id);
	
	
	/**
	 * Corresponde a una peticion http delete que se encarga de eliminar una cancion de la lista de favoritas de un oyente
	 * 
	 * @param idOyente  es el id del usuario oyente.
	 * @param idFav es el id de la cancion que se encuentra en favoritas.
	 * @return ResponseEntity con el mensaje correspondiente al resultado de la operacion.
	 */
	@DeleteMapping(value = "/favoritas/delete/{idoyente}/{idfavorita}")
	public ResponseEntity<String>  deleteFavorita(@PathVariable(name = "idoyente")Long idoyente ,
			@PathVariable(name = "idfavorita") Long idFavorita);
		

	/**
	 * Corresponde a una peticion http asociada al HttpMethod Post, 
	 * agrega una cancion a las favoritas de un oyente en la aplicacion.
	 * 
	 * @param idOyente es el id del usuario oyente.
	 * @param idCancion es el id de la usuario cancion.
	 * @return  ResponseEntity con un mensaje en el body que indica el resultado de la peticion.
	 */
	@PostMapping(value = "/favoritas/save/{idoyente}/{idcancion}")
	public ResponseEntity<String>  addFavorita(@PathVariable(name = "idoyente")Long idOyente, @PathVariable(name = "idcancion")Long idCancion);

	
	
	/**
	 * Corresponede a una peticion http asociada al HttpMethod Get, 
	 * se encarga de obtener una cancion que se encuentra en las favoritas del oyente.
	 * 
	 * @param idoyente es el id del usuario oyente.
	 * @param idFavorita es el id de la cancion favorita, este id no es el mismo de la cancion.
	 * @return ResponseEntity con la cancion favorita en el body.
	 */
	@GetMapping(value = "/favoritas/search/{idoyente}/{idfavorita}")
	public ResponseEntity<String> getfavorita( @PathVariable(name = "idoyente")Long idoyente ,@PathVariable(name = "idfavorita") Long idFavorita);
	
	
}
