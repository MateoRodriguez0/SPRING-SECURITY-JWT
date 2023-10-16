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


@RestController
@RequestMapping(value = "/musicapp/auth")
public class AuthController {

	
	@PostMapping(value = "/singin")
	public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authenticationRequest){
		
		return ResponseEntity.accepted().body(userServices.login(authenticationRequest));
	}
	
	@PostMapping(value = "/singup")
	public ResponseEntity<Object> singUp(@Valid @RequestBody UserRegistration userRegistration,
			BindingResult bindingResult){
		

		if(bindingResult.hasErrors()) {
			

			return ResponseEntity.badRequest()
					.body(bindingResult.getAllErrors()
							.stream()
							.map(o ->o.getDefaultMessage())
							.collect(Collectors.toList()));
		}
		
		if(userRegistration.getIdRol()==2 && userRegistration.getNombreArtistico()==null) {
			return ResponseEntity.badRequest().body("Hace falta el nombre artistico");
		}
			
		
		return ResponseEntity.ok(userServices.SingUp(userRegistration));
	}
	
	@Autowired
	private userServices userServices;
}
