package com.app.musica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.musica.repositories.ArtistasRepository;
import com.app.musica.repositories.OyentesRepository;
import com.app.musica.security.userdetails.User;


/**
 * clase de configuracion de de spring security.
 * contiente los beans necesarios para configurar correctamente la seguridad en la aplicacion.
 * 
 * @Author Mateo Rodriguez c.
 * 12 oct. 2023 8:19:56 a. m.
 */
@Configuration
public class ApplicationSecurityConfiguration {

	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager(); 
	}
	
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		
		return daoAuthenticationProvider;
	}
	
	
	
	
	@Bean
	PasswordEncoder passwordEncoder () {
		
		return new BCryptPasswordEncoder();
	}
	

	@Bean 
	UserDetailsService userDetailsService() {
		
		return  username ->{
			if(artistasRepository.existsByEmail(username)) {
				
				return new User(artistasRepository.findByEmail(username).get(),null);
						
			}
			
			else if(oyentesRepository.existsByEmail(username)) {

				return new User(null,oyentesRepository.findByEmail(username).get());
			}
			
			else {
				throw new UsernameNotFoundException("El usuario no existe");
			}
			
		};
	}
	
	
	
	@Autowired
	private ArtistasRepository artistasRepository;
	@Autowired
	private OyentesRepository oyentesRepository;
	
	
	
	} 
