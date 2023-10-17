package com.app.musica.controllers;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.musica.models.UserRegistration;
import com.app.musica.models.auth.AuthenticationRequest;
import com.app.musica.models.auth.AuthenticationResponse;
import com.app.musica.services.userServices;

import jakarta.validation.Valid;

/**
 * Controlador que maneja las peticiones de registo de cuenta e inicio de sesion.
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 3:43:24 p. m.
 */
@RestController
@RequestMapping(value = "/musicapp/auth")
public class AuthController {

	/**
	 * Este metodo Realiza la autenticacion de usuario, recibe el AuthenticationRequest 
	 * que sera validado por una clase de servicio.
	 * 
	 * @param authenticationRequest Este objeto contiene el username y password
	 * para autenticar el usuario a traves del metodo login de la interfaz UserServices.
	 * 
	 * @return un AuthenticationResponse que contiene el jwt.
	 */
	@PostMapping(value = "/singin")
	public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authenticationRequest){
		
		return ResponseEntity.accepted().body(userServices.login(authenticationRequest));
	}
	
	/**
	 * Este metodo Registra un usuario nuevo en la app. 
	 * Ademas valida el objeto UserRegistration para evitar que hayan errores. 
	 * 
	 * @param userRegistration contiene los datos del usuario que se registrara.
	 * @param bindingResult contiene informacacion de la validacion de nuestro UserRegistration. 
	 * @return puede devolver una lista con los errores de validacion 
	 * o algun mensaje que describa el estado de la peticion.
	 */
	@PostMapping(value = "/singup")
	public ResponseEntity<Object> singUp(@Valid @RequestBody UserRegistration userRegistration,
			BindingResult bindingResult){
		

		if(bindingResult.hasErrors()) {

			return ResponseEntity.badRequest().body(bindingResult.getAllErrors()
					.stream()
					.map(o ->o.getDefaultMessage())
					.collect(Collectors.toList()));
		}
		
		else if(userRegistration.getIdRol()==2 && userRegistration.getNombreArtistico()==null) {
			
			return ResponseEntity.badRequest().body("Hace falta el nombre artistico");
		}
			
		return ResponseEntity.ok(userServices.SingUp(userRegistration));
	}
	
	@Autowired
	private userServices userServices;
}
