package com.app.musica.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Clase de modelo que representa las credenciales de inicio de sesion de un usuario.
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 2:31:39 p. m.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

	
	private String username;
	private String password;
}
