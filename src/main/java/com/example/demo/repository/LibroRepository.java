package com.example.demo.repository;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
	
	@Query("select l from Libro l where l.fechaPublicacion >= :fecha")
    public List<Libro> findAllWithCreationDateTimeBefore(
      @Param("fecha") Date  fecha);
	
}
