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

import com.js.plataformasalud.modelos.entidades.Consultorio;
import com.js.plataformasalud.modelos.servicios.IConsultorioServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class ConsultorioRestController {
	private IConsultorioServiceImpl consultorioService;

	public ConsultorioRestController(IConsultorioServiceImpl consultorioService) {
		super();
		this.consultorioService = consultorioService;
	}
	
	@GetMapping("/consultorio")
	public List<Consultorio> index(){
		return consultorioService.findAll();
		
	}
	
	@GetMapping("/consultorio/{idconsultorio}")
	@ResponseStatus(HttpStatus.OK)
	public Consultorio mostrar(@PathVariable Long idconsultorio) {
		return consultorioService.FindById(idconsultorio);
	}
	
	@PostMapping("/consultorio")
	@ResponseStatus(HttpStatus.CREATED)
	public Consultorio crear (@RequestBody Consultorio consultorio) {
		return consultorioService.save(consultorio);
	}
	
	@PutMapping("/consultorio/{idconsultorio}")
	public Consultorio update (@RequestBody Consultorio consultorio, @PathVariable Long idconsultorio) {
		Consultorio consActual = consultorioService.FindById(idconsultorio);
		
		consActual.setEstado(consultorio.getEstado());
		consActual.setNomconsul(consultorio.getNomconsul());
				
		
		return consultorioService.save(consActual);
	}

	
	

}
