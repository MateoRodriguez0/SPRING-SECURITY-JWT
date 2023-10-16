package com.app.musica.security.userdetails;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.musica.models.Artista;
import com.app.musica.models.Oyente;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		if(artista!=null) {
			return List.of(new SimpleGrantedAuthority(artista.getRol().getNombre()));
		}
		
		return List.of(new SimpleGrantedAuthority(oyente.getRol().getNombre()));
	}

	@Override
	public String getPassword() {
		
		if(artista!=null) {
			
			return artista.getPassword();
		}
		
		return oyente.getPassword();
	}

	@Override
	public String getUsername() {
		
		if(artista!=null) {
			return artista.getEmail();
		}
		
		return oyente.getEmail() ;
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	
	private Artista artista;
	
	private Oyente oyente;
}
