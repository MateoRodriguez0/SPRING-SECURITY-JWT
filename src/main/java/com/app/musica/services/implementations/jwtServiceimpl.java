package com.app.musica.services.implementations;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.app.musica.services.JwtServices;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Clase de sericio que implementa la interfaz JwtServices y provee 
 * los metodos necesarios para crear y validar los JWT de autenticacion.
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 4:12:37 p. m.
 */
@Service
public class jwtServiceimpl implements JwtServices{

	

	@Override
	public String generarToken(UserDetails userDetails, Map<String, Object> claims) {
	
		Date fechaCreacion=new Date(System.currentTimeMillis());
		Date fechaExpiracion= new Date(fechaCreacion.getTime()+(minutosExpiracion*60*1000));
		
		String jwt=Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(fechaCreacion)
				.setExpiration(fechaExpiracion)
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.signWith(generarkey(),SignatureAlgorithm.HS256)
				.compact();
	
	
		return jwt;
	}

	

	@Override
	public String ExtracUsername(String jwt) {
		
		return extraClaims(jwt)
				.getSubject();
	}
	
	@Override
	public Key generarkey() {
		
		 byte[] encodedSecretKey = Base64.getDecoder().decode(key);
	
		return Keys.hmacShaKeyFor(encodedSecretKey);
	}
	
	@Override
	public Claims extraClaims(String jwt) {
		
		return Jwts.parserBuilder()
				.setSigningKey(generarkey())
				.build()
				.parseClaimsJws(jwt)
				.getBody();
	}
	
	@Value("${security.jwt.key}")
	private String key;
	
	
	@Value("${security.jwt.expiration}")
	private Integer minutosExpiracion;

}
