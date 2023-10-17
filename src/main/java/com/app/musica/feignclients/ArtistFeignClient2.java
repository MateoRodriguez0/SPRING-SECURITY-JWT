package com.app.musica.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.musica.models.Cancion;
/**
 * Esta intefaz Representa un cliente feign que consume la API restful servicio-artistas. 
 * en este cliente se encuentran las peticiones del servicio artistas que seran disponibles solo para usuarios con permisos de artistas. 
 * Se encuentra disponible en el host localhost y esta usando el puerto 8004.
 *  
 * @Author Mateo Rodriguez c.
 * 13 oct. 2023 7:45:29 a. m.
 */

@FeignClient(name = "servicio-artistas-protegidos",url = "localhost:8004")
public interface ArtistFeignClient2 {

	/**
	 * Este metodo utiliza la API de artistas para publicar una cancion.
	 * 
	 * @param cancion objeto que contiene los datos de la cancion que sera publicada.
	 * @param id del artista que quiere publicar la cancion.
	 * @return la respuesta de la API artistas.
	 */
	@PostMapping(value = "/canciones/save/{idArtista}")
	public ResponseEntity<String> publicarCancion(@RequestBody Cancion cancion,@PathVariable(name = "idArtista")Long id);
	

	/**
	 *  Este metodo utiliza la API de artistas para actualizar una cancion.  
	 * 
	 * @param idAritsta id del artista que quiere publicar la cancion.
	 * @param cancion objeto que contiene los datos con el id de la cancion que ser actualizada.
	 * @return la respuesta de la API artistas.
	 */
	@PutMapping(value = "/canciones/update/{idArtista}")
	public ResponseEntity<String> updateCancion(@PathVariable(name = "idArtista") Long idAritsta,
			@RequestBody Cancion cancion);
	
	

	/**
	 * Este metodo se utiliza la API de artistas para eliminar una cancion. 
	 * 
	 * @param idArtista id del artista que quiere publicar la cancion.
	 * @param idCancion id de la cancion que sera eliminada.
	 * @return la respuesta de la API artistas.
	 */
	@DeleteMapping(value = "/canciones/delete/{idArtista}/{idCancion}")
	public ResponseEntity<String> deleteCancion(@PathVariable(name = "idArtista") Long idArtista,
			@PathVariable(name = "idCancion") Long idCancion);
}
