package com.app.musica.services;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.app.musica.models.UserRegistration;
import com.app.musica.models.auth.AuthenticationRequest;
import com.app.musica.models.auth.AuthenticationResponse;

public interface userServices {

	/**
	 * Este metodo realiza la autenticacion del usuario que trata de iniciar en la aplicacion, 
	 * procesa los datos y crea un token que contiene la informacion del usuario authenticado
	 * 
	 * @param authenticationRequest representa a un usuario que trata de iniciar sesion
	 * @return AuthenticationResponse que contiene el jwt
	 */
	AuthenticationResponse login(AuthenticationRequest authenticationRequest);
	
	
	/**
	 * 
	 * Este metodo verifica que tipo de usuario se quiere registrar y crea un
	 * nuevo objeto que será un usuario del tipo de la entidad que esta mapeada a la base de datos.
	 * Codifica la clave del usuario y lo guarda en la base de datos.
	 *  
	 * 
	 * @param userRegistration es un objeto que contiene los datos necesarios 
	 * para que un usuario se pueda registrar en la aplicacion.
	 * 
	 * @return una cadena que describe el resultado de la operacion
	 */
	String SingUp(UserRegistration userRegistration);
	
	
	
	/**
	 * crea un Map que contiene algunos datos que iran en el playload del jwt token para tener la informacion necesaria del usuario en el token
	 * 
	 * @param userDetails  se usa para guardar el username y los credentials del usuario en el playload del token
	 * @return Map con una serie de atributos que seran enviadas al servicio de generar token.
	 */
	Map<String, Object> generateExtraClaims(UserDetails userDetails);
	
	

}
