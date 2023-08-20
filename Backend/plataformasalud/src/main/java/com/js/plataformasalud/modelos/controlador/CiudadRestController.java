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

import com.js.plataformasalud.modelos.entidades.Ciudad;
import com.js.plataformasalud.modelos.servicios.ICiudadServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class CiudadRestController {
	
	private ICiudadServiceImpl ciuservice;

	public CiudadRestController(ICiudadServiceImpl ciuservice) {
		super();
		this.ciuservice = ciuservice;
	}
	
	@GetMapping("/ciudades")
	public List<Ciudad> index(){
		return ciuservice.findAll();
	}
	
	@GetMapping("/ciudades/{codciudad}")
	public Ciudad mostrar(@PathVariable Long codciudad) {
		return ciuservice.FindById(codciudad);
	}
	
	@PostMapping("/ciudades")
	public Ciudad save (@RequestBody Ciudad ciudad) {
		return ciuservice.save(ciudad);
	}
	
	@PutMapping("/ciudades/{codciudad}")
	public Ciudad actualizar (@PathVariable Long codciudad,@RequestBody Ciudad ciudad) {
		
		Ciudad ciuAct = ciuservice.FindById(codciudad);
		
		ciuAct.setDepartamento(ciudad.getDepartamento());
		ciuAct.setNomciudad(ciudad.getNomciudad());
		
		return ciuservice.save(ciuAct);
	}

}
