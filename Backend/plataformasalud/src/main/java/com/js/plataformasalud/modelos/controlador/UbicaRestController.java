package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.Ubicacion;
import com.js.plataformasalud.modelos.servicios.IUbicaServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class UbicaRestController {
	
	private IUbicaServiceImpl ubicaservice;
	
	@GetMapping("/ubicaciones")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Ubicacion> index(){
		return ubicaservice.findAll();
	}
	
	@GetMapping("/ubicaciones/{idubicaciones}")
	@ResponseStatus(code = HttpStatus.OK)
	public Ubicacion mostrar (@PathVariable Long idubicaciones) {
		return ubicaservice.findById(idubicaciones);
	}
	
	@PostMapping("/ubicaciones")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Ubicacion crear (@RequestBody Ubicacion ubica) {
		return ubicaservice.save(ubica);
	}
	
	@PutMapping("/ubicaciones/{idubicaciones}")
	@ResponseStatus(code = HttpStatus.OK)
	public Ubicacion update (@RequestBody Ubicacion ubica, @PathVariable Long idubicaciones) {
		Ubicacion ubicaActual = ubicaservice.findById(idubicaciones);
		
		ubicaActual.setEstado(ubica.getEstado());
		ubicaActual.setNomubicaciones(ubica.getNomubicaciones());
		
		return ubicaservice.save(ubicaActual);
	}

}
