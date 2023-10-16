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

@Component
public class JwtauthenticationFilter extends OncePerRequestFilter {

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
			authenticationToken=new UsernamePasswordAuthenticationToken(oyente.getEmail(),null,user.getAuthorities());
		}
		
		else if(artistasRepository.existsByEmail(subject)) {
			Artista  artista= artistasRepository.findByEmail(subject).get();
			User user= new User(artista, null);
			authenticationToken=new UsernamePasswordAuthenticationToken(artista.getEmail(),null,user.getAuthorities());
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
