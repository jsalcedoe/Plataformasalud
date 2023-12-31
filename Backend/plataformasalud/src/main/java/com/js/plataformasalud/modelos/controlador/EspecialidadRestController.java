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

import com.js.plataformasalud.modelos.entidades.Especialidades;
import com.js.plataformasalud.modelos.servicios.IEspServicesImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class EspecialidadRestController {
	
	
	private IEspServicesImpl espservice;
	
	@GetMapping("/especialidades")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Especialidades> index(){
		return espservice.findAll();
	}
	
	@GetMapping("/especialidades/{idespecialidad}")
	@ResponseStatus(code = HttpStatus.OK)
	public Especialidades mostrar (@PathVariable Long idespecialidad) {
		return espservice.findById(idespecialidad);
	}
	
	@PostMapping("/especialidades")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Especialidades crear (@RequestBody Especialidades esp) {
		
		return espservice.save(esp);
		
	}
	
	@PutMapping("/especialidades/{idespecialidad}")
	@ResponseStatus(code = HttpStatus.OK)
	public Especialidades update (@RequestBody Especialidades esp, @PathVariable Long idespecialidad) {
		
		Especialidades espActual = espservice.findById(idespecialidad);
		espActual.setEspecialidad(esp.getEspecialidad());
		espActual.setEstado(esp.getEstado());
		
		return espservice.save(espActual);
	}
	

}
