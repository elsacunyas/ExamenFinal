package com.example.demo.model;

import java.util.Date;

public class DetalleLibros {
	
	private Long  idLib;
	private String libro;
	private String autor;
	private String genero;
	private Date publicacion;
	
	public Long getIdLib() {
		return idLib;
	}
	public void setIdLib(Long idLib) {
		this.idLib = idLib;
	}
	public String getLibro() {
		return libro;
	}
	public void setLibro(String libro) {
		this.libro = libro;
	}
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(Date publicacion) {
		this.publicacion = publicacion;
	}
	
	
	
}