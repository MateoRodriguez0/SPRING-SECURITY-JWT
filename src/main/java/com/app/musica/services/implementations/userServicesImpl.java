package com.app.musica.services.implementations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.musica.models.Artista;
import com.app.musica.models.Oyente;
import com.app.musica.models.Rol;
import com.app.musica.models.UserRegistration;
import com.app.musica.models.auth.AuthenticationRequest;
import com.app.musica.models.auth.AuthenticationResponse;
import com.app.musica.repositories.ArtistasRepository;
import com.app.musica.repositories.OyentesRepository;
import com.app.musica.security.userdetails.User;
import com.app.musica.services.JwtServices;
import com.app.musica.services.userServices;
/**
 * Esta clase implementa la interfaz userServices que provee los metodos 
 * que realizan algunas tareas necesarias para la seguridad de la aplicacion.
 * 
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 2:43:22 p. m.
 */
@Service
@Primary
public class userServicesImpl implements userServices{

	@Override
	public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
		
		// obtenemos el username del authenticationRequest
		String username=authenticationRequest.getUsername();
		// obtenemos el password del authenticationRequest
		String password= authenticationRequest.getPassword();
		
		// creamos un UsernamePasswordAuthenticationToken con los datos del authenticationRequest
		UsernamePasswordAuthenticationToken authenticationToken= 
				new UsernamePasswordAuthenticationToken(username, password);
		
		// autnticamos el el authentiacationManager de SpringSecurity 
		authenticationManager.authenticate(authenticationToken);
		
		/**
		 * si el autenticacionmanager es corecto realiza las demas lineas, 
		 * de lo contrario devuelve un UsernameNotFoundException("El usuario no existe")	
		 */
		
		Optional<Oyente>oyente =oyentesRepository.findByEmail(username);
		Optional<Artista> artista = artistasRepository.findByEmail(username);
		
		if(artista.isPresent()) {
			User user=new User(artista.get(),null);
			String jwt=jwtServices.generarToken(user, generateExtraClaims(user));
			return new AuthenticationResponse(jwt);
		}
		
		else {
	
			User user=new User(null,oyente.get());
			String jwt=jwtServices.generarToken(user, generateExtraClaims(user));
			
			return new AuthenticationResponse(jwt);
		}
		
		
	}

	

	@Override
	public Map<String, Object> generateExtraClaims(UserDetails userDetails) {
	
		Map<String, Object> extraClaims=new HashMap<>();
		
		extraClaims.put("username",userDetails.getUsername());;
		
		extraClaims.put("role",userDetails.getAuthorities()
				.stream()
				.map(g -> g.getAuthority())
				.findFirst()
				.get());
				
		return extraClaims;
	}


	
	
	@Override
	public String SingUp(UserRegistration user) {
		
		if(artistasRepository.existsByEmail(user.getEmail()) ||
				oyentesRepository.existsByEmail(user.getEmail())){
			
			return UserExistente;
		}
		
		String password=passwordEncoder.encode(user.getPassword());
		
	
		if(user.getIdRol()==1) {
			
			Oyente oyente= new Oyente();
			Rol rol = new Rol();
			oyente.setNombre(user.getNombre());
			oyente.setEmail(user.getEmail());
			oyente.setPassword(password);
			
			rol.setId(user.getIdRol());
			oyente.setRol(rol);
			
			oyentesRepository.save(oyente);
		}
		
		if(user.getIdRol()==2) {
			
			Artista artista= new Artista();
			Rol rol = new Rol();
			
			artista.setNombre(user.getNombre());
			artista.setEmail(user.getEmail());
			artista.setNombreArtistico(user.getNombreArtistico());
			artista.setPassword(password);
	
			rol.setId(user.getIdRol());
			artista.setRol(rol);
			
			artistasRepository.save(artista);
			
		}
		
		return UserRegistrado;
	}
	
	
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ArtistasRepository artistasRepository;
	
	@Autowired
	private OyentesRepository oyentesRepository;
	
	@Autowired
	private JwtServices jwtServices;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final String UserExistente="usted Ya se encuentra registrado";
	
	private final String UserRegistrado="usted se ha registrado con exito";

}
