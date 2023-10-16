package com.app.musica.feignclients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Esta intefaz Representa un cliente feign que consume la API restful servicio-artistas. 
 * en este cliente se encuentran las peticiones del servicio artistas que estaran disponibles para todos los usuarios. 
 * Se encuentra disponible en el host localhost y esta usando el puerto 8004.
 * 
 * @Author Mateo Rodriguez c.
 * 12 oct. 2023 7:57:47 a. m.
 */
@FeignClient(name = "servicio-artistas",url = "localhost:8004")
public interface ArtistsFeignClient {

	/**
	 * 
	 * @return  la lista de canciones publicadas en la app.
	 */
	@GetMapping(value = "/canciones/explorar")
	public ResponseEntity<?> explorarCanciones();
	
	
	/**
	 * 
	 * @return la lista de artistas en la app. 
	 */
	@GetMapping(value = "/canciones/artistas")
	public ResponseEntity<?> listarArtistas();
	
	
	/**
	 * 
	 *
	 * @param idArtista id del artista que se desea ver sus canciones.
	 * @return lista de canciones del artista
	 */
	@GetMapping(value = "/canciones/artista/{idArtista}")
	public ResponseEntity<?> cancionesPorArtista(@PathVariable(name = "idArtista")Long idArtista);
	
	
	@GetMapping(value = "/canciones/search/{idCancion}")
	public ResponseEntity<Object> buscarCancion(@PathVariable(name = "idCancion") Long idCancion);
}
