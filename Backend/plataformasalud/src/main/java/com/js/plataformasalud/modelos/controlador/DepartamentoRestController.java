package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.Departamento;
import com.js.plataformasalud.modelos.servicios.IDepartamentoServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class DepartamentoRestController {
	
	private IDepartamentoServiceImpl depservice;

	public DepartamentoRestController(IDepartamentoServiceImpl depservice) {
		super();
		this.depservice = depservice;
	}
	
	@GetMapping("/departamentos")
	public List<Departamento> index(){
		return (List<Departamento>) depservice.findAll();
	}
	
	@GetMapping("/departamentos/{coddep}")
	public Departamento mostrar (@PathVariable Long coddep) {
		return depservice.findById(coddep);
	}
	
	@PostMapping("/departamentos")
	public Departamento crear (@RequestBody Departamento departamento) {
		return depservice.save(departamento);
	}
	
	@PutMapping("/departamentos/{coddep}")
	public Departamento actualizar (@PathVariable Long coddep, @RequestBody Departamento departamento) {
		Departamento depAct = depservice.findById(coddep);
		
		depAct.setDepartamento(departamento.getDepartamento());
		
		return depservice.save(depAct);
	}

}
