package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "genero")
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idgenero;
	
	@Column(name = "nombreGenero",nullable = false,length = 45)
	public String nombreGenero;

	public Long getIdgenero() {
		return idgenero;
	}

	public void setIdgenero(Long idgenero) {
		this.idgenero = idgenero;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	
	
	

}
