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
