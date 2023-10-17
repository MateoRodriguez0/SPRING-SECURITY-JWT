package com.app.musica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Clase de configuracion de la seguridad en la aplicacion.
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 3:59:17 p. m.
 */
@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

	/**
	 * Este metodo utiliza un objeto HttpSecurity para configurar el tipo de sesion, el manejo de tokens, 
	 * proteger todos los endpoints necsarios y asignar la autoridad permitida para cada url 
	 * y agrega el filtro de auenticacion personalizado antes del UsernamePasswordAuthenticationFilter 
	 * en el FilterOrderRegistration.
	 * 
	 * 
	 * @param httpSecurity 
	 * @return SecurityFilterChain que contiene la configuracion de la authorizacion.
	 * @throws Exception
	 */
	@Bean 
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf(c -> c.disable())
		.sessionManagement(s ->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtauthenticationFilter,UsernamePasswordAuthenticationFilter.class)
		.authorizeHttpRequests(http -> http
				
				.requestMatchers("/musicapp/favorites/**").hasAnyAuthority("OYENTE")
				.requestMatchers("/musicapp/mysongs/**").hasAnyAuthority("ARTISTA")
				
				.requestMatchers(HttpMethod.GET, "/musicapp/artistas").authenticated()
				.requestMatchers(HttpMethod.GET, "/musicapp/artista/**").authenticated()
				.requestMatchers(HttpMethod.GET,"/musicapp/songs/**").authenticated()
				.requestMatchers(HttpMethod.GET, "/musicapp/home").permitAll()
				.requestMatchers("/musicapp/auth/**").anonymous()
				
				.requestMatchers("/error").permitAll()
				.anyRequest().denyAll());
		
		return httpSecurity.build();
	}
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	
	@Autowired
	private JwtauthenticationFilter jwtauthenticationFilter;
}
