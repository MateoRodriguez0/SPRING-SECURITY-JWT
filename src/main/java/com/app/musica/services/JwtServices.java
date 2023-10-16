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


	
	Key generarkey();

	String ExtracUsername(String jwt);
	
	String generarToken(UserDetails userDetails,Map<String, Object>  claims);
	
	Claims extraClaims(String jwt);
	

	
	
}
