package com.example.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DetalleLibros;
import com.example.demo.model.Genero;
import com.example.demo.model.Libro;
import com.example.demo.repository.GeneroRepository;
import com.example.demo.repository.LibroRepository;

@Service
public class LibroService {

	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private GeneroRepository generoRepository;

	public List<Libro> getAllLibro() {
		return libroRepository.findAll();
	}

	public Libro createLibro(Libro libro) {
		return libroRepository.save(libro);
	}

	public void deleteLibro(Long id) {
		libroRepository.deleteById(id);
	}

	public Libro getLibroById(Long id) {
		return libroRepository.findById(id).orElse(null);
	}

	public Genero getGenero(Long idGenero) {
		return generoRepository.findById(idGenero).orElse(null);
	}

	public List<DetalleLibros> getDetalle() {
		List<Libro> listLibros = getAllLibro();

		List<DetalleLibros> dtLibros = new ArrayList<DetalleLibros>();
		for (Libro libro : listLibros) {

			DetalleLibros dLibros = new DetalleLibros();
			dLibros.setIdLib(libro.getIdLibro());
			dLibros.setLibro(libro.getNombreLibro());
			dLibros.setAutor(libro.getNombreAutor());
			dLibros.setPublicacion(libro.getFechaPublicacion());
			dLibros.setGenero(libro.getGenero().nombreGenero);

			dtLibros.add(dLibros);

		}
		return dtLibros;
	}

	public int getCantidad() {

		Calendar fecha = Calendar.getInstance();
		fecha.add(Calendar.MONTH, -6);

		return libroRepository.findAllWithCreationDateTimeBefore(fecha.getTime()).size();

	}

}
