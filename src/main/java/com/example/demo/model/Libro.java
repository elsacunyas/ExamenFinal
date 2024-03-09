package com.example.demo.model;



import java.util.Date;

import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idLibro;
	
	@Column(name = "nombreLibro",nullable = false,length = 60)
	@Size(min = 4,max = 60 )
	public String nombreLibro;
	
	@Column(name = "nombreAutor",nullable = false,length = 60)
	@Size(min = 4,max = 60 )
	public String nombreAutor;
	
	@Column(name = "fechaPublicacion")
	public Date fechaPublicacion;
	
	
	
	@ManyToOne
	@JoinColumn(name = "idgenero",nullable = false)
	public Genero genero;



	public Long getIdLibro() {
		return idLibro;
	}



	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}



	public String getNombreLibro() {
		return nombreLibro;
	}



	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}



	public String getNombreAutor() {
		return nombreAutor;
	}



	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}



	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}



	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}



	public Genero getGenero() {
		return genero;
	}



	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	
	

}
