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

import com.js.plataformasalud.modelos.entidades.Diagnostico;
import com.js.plataformasalud.modelos.servicios.IDxServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class dxRestController {
	
	
	private IDxServiceImpl dxservice;
	
	@GetMapping("/diagnosticos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Diagnostico> index(){
		return dxservice.findAll();
	}
	
	@GetMapping("/diagnosticos/{iddx}")
	@ResponseStatus(code = HttpStatus.OK)
	public Diagnostico mostrar(@PathVariable String iddx) {
		return dxservice.findById(iddx);
	}
	
	@PostMapping("/diagnosticos")
	@ResponseStatus(code =  HttpStatus.CREATED)
	public Diagnostico crear (@RequestBody Diagnostico diagnostico) {
		return dxservice.save(diagnostico);
	}
	
	@PutMapping("/diagnosticos/{iddx}")
	@ResponseStatus(code = HttpStatus.OK)
	public Diagnostico actualiza (@RequestBody Diagnostico diagnostico, @PathVariable String iddx) {
		Diagnostico dxActual = dxservice.findById(iddx);
		
		dxActual.setCapdx(diagnostico.getCapdx());
		dxActual.setDescdx(diagnostico.getDescdx());
		dxActual.setEdadmaxdx(diagnostico.getEdadmaxdx());
		dxActual.setEdadmindx(diagnostico.getEdadmindx());
		dxActual.setEstdx(diagnostico.getEstdx());
		dxActual.setNomdx(diagnostico.getNomdx());
		dxActual.setSexdx(diagnostico.getSexdx());
		
		return dxservice.save(dxActual);
	}

}
