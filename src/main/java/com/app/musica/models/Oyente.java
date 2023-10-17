package com.app.musica.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase de modelo que represeanta a un usuario de tipo oyente.
 * Esta clase esta mapeada a la entidad oyentes en la abse de datos 
 * y tiene una relacion de muchos a uno con la entidad rol;
 * 
 * @Author Mateo Rodriguez c.
 * 17 oct. 2023 2:34:44 p. m.
 */
@Entity
@Table(name = "oyentes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Oyente  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "email",unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "idRol")
	private Rol rol;
	



}
