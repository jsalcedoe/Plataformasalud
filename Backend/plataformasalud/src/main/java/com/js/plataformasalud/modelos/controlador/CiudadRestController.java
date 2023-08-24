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

import com.js.plataformasalud.modelos.entidades.Ciudad;
import com.js.plataformasalud.modelos.servicios.ICiudadServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class CiudadRestController {
	
	private ICiudadServiceImpl ciuservice;

	/*public CiudadRestController(ICiudadServiceImpl ciuservice) {
		super();
		this.ciuservice = ciuservice;
	}*/
	
	@GetMapping("/ciudades")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Ciudad> index(){
		return ciuservice.findAll();
	}
	
	@GetMapping("/ciudades/{codciudad}")
	@ResponseStatus(code = HttpStatus.OK)
	public Ciudad mostrar(@PathVariable Long codciudad) {
		return ciuservice.FindById(codciudad);
	}
	
	@PostMapping("/ciudades")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Ciudad save (@RequestBody Ciudad ciudad) {
		return ciuservice.save(ciudad);
	}
	
	@PutMapping("/ciudades/{codciudad}")
	@ResponseStatus(code = HttpStatus.OK)
	public Ciudad actualizar (@PathVariable Long codciudad,@RequestBody Ciudad ciudad) {
		
		Ciudad ciuAct = ciuservice.FindById(codciudad);
		
		ciuAct.setDepartamento(ciudad.getDepartamento());
		ciuAct.setNomciudad(ciudad.getNomciudad());
		
		return ciuservice.save(ciuAct);
	}

}
