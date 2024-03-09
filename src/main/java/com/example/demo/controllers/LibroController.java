package com.example.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.DetalleLibros;
import com.example.demo.model.Genero;
import com.example.demo.model.Libro;
import com.example.demo.service.GeneroService;
import com.example.demo.service.LibroService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/libros")
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/libro")
	public String getAllLibro(Model model) {
		
		List<Libro> listLibros = libroService.getAllLibro();
		
		model.addAttribute("libros",listLibros);
		
		return "libroList";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos",generoService.getAllGenero());
		return "libroRegister";
	}
	
	@PostMapping("/register")
	public String createLibro(@RequestParam("nombreLibro") String nombreLibro,@RequestParam("nombreAutor")String nombreAutor,
			@RequestParam("fechaPublicacion") Date fechaPublicacion,@RequestParam("idgenero") Long idgenero, Model model) {
		
		Libro libro = new Libro();
		Genero genero = libroService.getGenero(idgenero);
		
		libro.nombreLibro = nombreLibro;
		libro.nombreAutor = nombreAutor;
		libro.fechaPublicacion = fechaPublicacion;
		libro.genero = genero;
		
		libroService.createLibro(libro);
		
		List<Libro>listLibros =libroService.getAllLibro();
		model.addAttribute("libros",listLibros);
		return "libroList";
	}
	
	@GetMapping("/edit/{idLibro}")
	public String getLibroById(@PathVariable Long idLibro,Model model) {
		
		model.addAttribute("generos", generoService.getAllGenero());
		
		Libro libro = libroService.getLibroById(idLibro);
		model.addAttribute("libro",libro);
		return "libroEdit";
	}
	
	@PostMapping("/edit")
	public String editLibro(@RequestParam("idLibro") Long idLibro,@RequestParam("nombreLibro") String nombreLibro,@RequestParam("nombreAutor")String nombreAutor,
			@RequestParam("fechaPublicacion") Date fechaPublicacion,@RequestParam("idgenero") Long idgenero, Model model) {
		
		Libro libro = libroService.getLibroById(idLibro);
		Genero genero = libroService.getGenero(idgenero);
		libro.nombreLibro = nombreLibro;
		libro.nombreAutor = nombreAutor;
		libro.fechaPublicacion = fechaPublicacion;
		libro.genero = genero;
		
		libroService.createLibro(libro);
		List<Libro> listLibros = libroService.getAllLibro();
		model.addAttribute("libros",listLibros);
		return "libroList";
		
	}
	
	@GetMapping("/delete/{idLibro}")
	public String deleteLibro(@PathVariable Long idLibro,Model model) {
		
		libroService.deleteLibro(idLibro);
		List<Libro> ListLibros = libroService.getAllLibro();
		model.addAttribute("libros", ListLibros);
		
		return "libroList";
	}
	
	@GetMapping("/report")
	public void reportProduc(HttpServletResponse response)throws JRException,IOException {
		InputStream jasperStream = this.getClass().getResourceAsStream("/reporte/ReporteLibro.jasper");
		
		Map<String, Object> params = new HashMap<>();
		params.put("usuario", "elsa patricia");
		
		
		
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(libroService.getDetalle());
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,dataSource);
		
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "filename=reporte_libro.pdf");
		
		final OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}

}
