package com.app.musica.models;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase de modelo que representa a un usuario que quiere registrase en la aplicacion.
 * Utiliza la validacion de jakarta para verificar que los datos sean validos.
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 2:46:19 p. m.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistration {


	
	@NotEmpty(message = "el nombre no puede estar vacio")
	private String nombre;

	/**
	 * es el nombre artistico de los artistas, solo es necesario cuando el idRol del UserRegistration sea igual a 2.
	 */
	private String nombreArtistico; 
	
	@Email(message = "el campo email no es valido ")
	@NotEmpty(message = "el email no puede ser nulo")
	private String email;
	

	@Length(min = 12,message = "la clave debe tener al menos 12 caractares")
	@NotEmpty(message = "la clave no puede estar vacia")
	@Pattern(regexp = "^\\S*$",message = "la clave no puede contener espacios")
	private String password;
	
	@Min(value = 1,message = "el campo idRol no es valido")
	@Max(value = 2,message = "el campo idRol no es valido")
	@NotNull(message = "el rol no puede estar vacio")
	private Long idRol;
}
