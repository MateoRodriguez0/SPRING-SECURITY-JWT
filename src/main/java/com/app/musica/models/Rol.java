package com.app.musica.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * clase de modelo que representa el rol o la autoridad de un usuario.
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 2:33:46 p. m.
 */
@Data
@Entity
@Table(name = "roles")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String Nombre;
}
