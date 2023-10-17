package com.app.musica.services;

import java.security.Key;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;


import io.jsonwebtoken.Claims;

/**
 * Esta interfaz contiene los metodos necesarios para realizar la authenticacion y poder manipular los jwt
 *  y realizar las operaciones necesarias para tener una buena estrategia de authenticacion.
 * 
 * @Author Mateo Rodriguez c.
 * 12 oct. 2023 10:45:39 a. m.
 */
public interface JwtServices {


	/**
	 * genera una clave para firmar y validar los tokens de autenticacion.
	 * @return devuelve la clave generada.
	 */
	Key generarkey();

	/**
	 * extrae el subject del JWT.  
	 * 
	 * @param jwt es el token codificado en base64.
	 * @return
	 */
	String ExtracUsername(String jwt);
	
	/**
	 * Crea un JWT a partir de los parametros, establece el parametro claims como parte del playload 
	 * y utiliza un userdetails para obtener username y settear el subject del JWT.
	 * 
	 * @param userDetails Es el usuario autenticado.
	 * @param claims Es un map que contiene algunas claves y valores que rian en el JWT.
	 * @return Devuele un String que contiene el JWT.
	 */
	String generarToken(UserDetails userDetails,Map<String, Object>  claims);
	
	/**
	 * extrae el Claims de un token con el proposito de poder proveer 
	 * el claims al metodo ExtracUsername(String jwt) de esta interfaz.
	 * 
	 * @param jwt es el token codificado con base64.
	 * @return Devuelve los claims del token.
	 */
	Claims extraClaims(String jwt);
	

	
	
}
