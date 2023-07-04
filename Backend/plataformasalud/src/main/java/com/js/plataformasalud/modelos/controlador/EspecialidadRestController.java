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

import com.js.plataformasalud.modelos.entidades.Especialidades;
import com.js.plataformasalud.modelos.servicios.IEspServicesImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class EspecialidadRestController {
	
	@Autowired
	private IEspServicesImpl espservice;
	
	@GetMapping("/especialidades")
	public List<Especialidades> index(){
		return espservice.findAll();
	}
	
	@GetMapping("/especialidades/{idespecialidad}")
	public Especialidades mostrar (@PathVariable Long idespecialidad) {
		return espservice.findById(idespecialidad);
	}
	
	@PostMapping("/especialidades")
	public Especialidades crear (@RequestBody Especialidades esp) {
		
		return espservice.save(esp);
		
	}
	
	@PutMapping("/especialidades/{idespecialidad}")
	public Especialidades update (@RequestBody Especialidades esp, @PathVariable Long idespecialidad) {
		
		Especialidades espActual = espservice.findById(idespecialidad);
		espActual.setEspecialidad(esp.getEspecialidad());
		espActual.setEstado(esp.getEstado());
		
		return espservice.save(espActual);
	}
	

}
