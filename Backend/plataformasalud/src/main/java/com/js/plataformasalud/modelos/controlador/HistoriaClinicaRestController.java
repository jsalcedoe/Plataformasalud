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

import com.js.plataformasalud.modelos.entidades.HistoriaClinica;
import com.js.plataformasalud.modelos.servicios.iHistoriaClinicaServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class HistoriaClinicaRestController {
	
	
	private iHistoriaClinicaServiceImpl hcpacservice;
	
	@GetMapping("/hcpac")
	@ResponseStatus(code = HttpStatus.OK)
	public List<HistoriaClinica> index(){
		
		return hcpacservice.findAll();
		
	}
	
	@GetMapping("/hcpac/{idhcpac}")
	@ResponseStatus(code = HttpStatus.OK)
	public HistoriaClinica mostrar (@PathVariable Long idhcpac) {
		
		return hcpacservice.findById(idhcpac);
		
	}
	
	@PostMapping("/hcpac")
	@ResponseStatus(code = HttpStatus.CREATED)
	public HistoriaClinica crear(@RequestBody HistoriaClinica hcpac) {
		
		return hcpacservice.save(hcpac);
	}
	
	@PutMapping("hcpac/idhcpac")
	@ResponseStatus(code = HttpStatus.OK)
	public HistoriaClinica editar (@RequestBody HistoriaClinica hcpac, @PathVariable Long idhcpac) {
		
		HistoriaClinica hcpacActual = hcpacservice.findById(idhcpac);
		
		hcpacActual.setAnalisishcpac(hcpac.getAnalisishcpac());
		hcpacActual.setAntalerhcpac(hcpac.getAntalerhcpac());
		hcpacActual.setAntfamyhcpac(hcpac.getAntfamyhcpac());
		hcpacActual.setAntfarmhcpac(hcpac.getAntfarmhcpac());
		hcpacActual.setAntpathcpac(hcpac.getAntpathcpac());
		hcpacActual.setAntqxhcpac(hcpac.getAntqxhcpac());
		hcpacActual.setAnttoxihcpac(hcpac.getAnttoxihcpac());
		hcpacActual.setEnfacthcpac(hcpac.getEnfacthcpac());
		hcpacActual.setEstaturahcpac(hcpac.getEstaturahcpac());
		hcpacActual.setEventohcpac(hcpac.getEventohcpac());
		hcpacActual.setFchcpac(hcpac.getFchcpac());
		hcpacActual.setFrhcpac(hcpac.getFrhcpac());
		hcpacActual.setImchcpac(hcpac.getImchcpac());
		hcpacActual.setMedico(hcpac.getMedico());
		hcpacActual.setMotconshcpac(hcpac.getMotconshcpac());
		hcpacActual.setObjhcpac(hcpac.getObjhcpac());
		hcpacActual.setPesohcpac(hcpac.getPesohcpac());
		hcpacActual.setPlanmanejhcpac(hcpac.getPlanmanejhcpac());
		hcpacActual.setRepexahcpac(hcpac.getRepexahcpac());
		hcpacActual.setTahcpac(hcpac.getTahcpac());
		hcpacActual.setTemphcpac(hcpac.getTemphcpac());
		hcpacActual.setEstado(hcpac.getEstado());
		
		return hcpacservice.save(hcpacActual);
	}
	
	

}
