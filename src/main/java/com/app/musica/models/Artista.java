package com.app.musica.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Artista {
	
	public Artista(Long id) {
		this.id=id;
	}
	
	private Long id;

	private String nombre;
	
	private List<Cancion> canciones;
}
