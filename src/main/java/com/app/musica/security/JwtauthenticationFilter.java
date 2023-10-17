package com.app.musica.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.musica.models.Artista;
import com.app.musica.models.Oyente;
import com.app.musica.repositories.ArtistasRepository;
import com.app.musica.repositories.OyentesRepository;
import com.app.musica.security.userdetails.User;
import com.app.musica.services.JwtServices;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Componente de seguridad, es un filtro personalizado que realiza la autenticacion de los tokens en las peticiones.
 * 
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 4:13:57 p. m.
 */

@Component
public class JwtauthenticationFilter extends OncePerRequestFilter {

	/**
	 * Este metodo Realiza las siguientes tareas en el orden que se describe:
	 * <p>
	 * 1. Obtiene el token a traves de los headers del request.
	 * <p>
	 * Solo si el token no esta en las cabeceras del request continua con los demas filtros de seguridad 
	 * y el metodo hace un return vacio, de lo contrario hace lo siguirente.
	 * <p>
	 * 2. utiliza una clase de servicio que extrae el username del token 
	 *  y a la vez valida que el token sea valido.
	 * <p>
	 * 3. luego busca en la base de datos el usuario con el email o username.
	 * <p>
	 * 4. Dependiendo del tipo de usuario que este asociado al username 
	 *    crea un objeto user que implementa UserDetails,
	 *    le pasa el objeto Oyente o Artista en el Construtor.
	 *  <p>
	 * 5. Por ultimo crea un UsernamePasswordAuthenticationToken, 
	 *    lo establece como authentication en el contexto de spring security 
	 *    y continua con el resto de filtros de spring security.
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authheader=request.getHeader("Authorization");
		
		if(authheader==null) {
			
			filterChain.doFilter(request, response);
			
			return;
		}
		String jwt=authheader.split(" ")[1];
		
		String subject=jwtServices.ExtracUsername(jwt);
		
		UsernamePasswordAuthenticationToken authenticationToken=null;
		
		if(oyentesRepository.existsByEmail(subject)) {
			
			Oyente oyente= oyentesRepository.findByEmail(subject).get();
			User user= new User(null, oyente);
			
			authenticationToken=new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());
		}
		
		else if(artistasRepository.existsByEmail(subject)) {
			
			Artista  artista= artistasRepository.findByEmail(subject).get();
			User user= new User(artista, null);
			
			authenticationToken=new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());
		}
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		
		filterChain.doFilter(request, response);
		
	}

	
	@Autowired
	private JwtServices jwtServices;
	
	@Autowired
	private ArtistasRepository artistasRepository;
	
	@Autowired
	private OyentesRepository oyentesRepository;
	
}
