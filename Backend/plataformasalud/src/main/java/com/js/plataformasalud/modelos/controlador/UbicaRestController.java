package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.Ubicacion;
import com.js.plataformasalud.modelos.servicios.IUbicaServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class UbicaRestController {
	@Autowired
	private IUbicaServiceImpl ubicaservice;
	
	@GetMapping("/ubicaciones")
	public List<Ubicacion> index(){
		return ubicaservice.findAll();
	}
	
	@GetMapping("/ubicaciones/{idubicaciones}")
	public Ubicacion mostrar (@PathVariable Long idubicaciones) {
		return ubicaservice.findById(idubicaciones);
	}
	
	@PostMapping("/ubicaciones")
	public Ubicacion crear (@RequestBody Ubicacion ubica) {
		return ubicaservice.save(ubica);
	}
	
	@PutMapping("/ubicaciones/{idubicaciones}")
	public Ubicacion update (@RequestBody Ubicacion ubica, @PathVariable Long idubicaciones) {
		Ubicacion ubicaActual = ubicaservice.findById(idubicaciones);
		
		ubicaActual.setEstado(ubica.getEstado());
		ubicaActual.setNomubicaciones(ubica.getNomubicaciones());
		
		return ubicaservice.save(ubicaActual);
	}

}
