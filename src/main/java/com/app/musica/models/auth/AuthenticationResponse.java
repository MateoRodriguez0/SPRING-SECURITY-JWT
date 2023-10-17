package com.app.musica.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * clase de modelo que representa el jwt de respuesta al realizar la autenticcion de unsuario.
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 2:31:55 p. m.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

	
	private String jwt;
}
