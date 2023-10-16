package com.app.musica.models;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cancion {

	public Cancion(String titulo,String genero, Time duracion) {
		this.titulo=titulo;
		this.genero=genero;
		this.duracion=duracion;
	
	}
	
	public Cancion(String titulo,String genero, Time duracion, List<Artista> artistas) {
		this(titulo, genero, duracion);
		this.artistas=artistas;	
	}


	private Long id;
	
	@NotEmpty(message = "El campo titulo no puede estar vacio")
	private String titulo;
	
	@NotEmpty(message = "El campo genero no puede estar vacio")
	private String genero;
	
	@NotNull(message = "El campo duracion no puede estar vacio")
    private Time duracion;
   
	private Timestamp estreno;

	
	private List<Artista> artistas;
	
	
	
}
