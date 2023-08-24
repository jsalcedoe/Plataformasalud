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

import com.js.plataformasalud.modelos.entidades.OrdMedIns;
import com.js.plataformasalud.modelos.servicios.IOrdMedInsServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class OrdMedInsRestController {
	
	
	private IOrdMedInsServiceImpl ordenservice;
	
	@GetMapping("/ordenesmedicamentos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<OrdMedIns> index(){
		return ordenservice.findAll();
		
	}
	
	@GetMapping("/ordenesmedicamentos/{idordmedins}")
	@ResponseStatus(code = HttpStatus.OK)
	public OrdMedIns mostrar(@PathVariable Long idordmedins) {
		return ordenservice.findById(idordmedins);
	}
	
	@PostMapping("/ordenesmedicamentos")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrdMedIns crear (@RequestBody OrdMedIns ord) {
		return ordenservice.save(ord);
	}
	
	@PutMapping("/ordenesmedicamentos/{idordmedins}")
	@ResponseStatus(code = HttpStatus.OK)
	public OrdMedIns actualizar(@RequestBody OrdMedIns ord,@PathVariable Long idordmedins) {
		
		OrdMedIns ordmedinsAct = ordenservice.findById(idordmedins);
		
		ordmedinsAct.setCantordmedins(ord.getCantordmedins());
		ordmedinsAct.setDosifmedins(ord.getDosifmedins());
		ordmedinsAct.setEstordmedins(ord.getEstordmedins());
		ordmedinsAct.setEventmedins(ord.getEventmedins());
		ordmedinsAct.setFechacreaordmedins(ord.getFechacreaordmedins());
		ordmedinsAct.setOrdmedins(ord.getOrdmedins());
		ordmedinsAct.setTiempoaplicmedins(ord.getTiempoaplicmedins());
		ordmedinsAct.setTipoordmedins(ord.getTipoordmedins());
		ordmedinsAct.setUsermedins(ord.getUsermedins());
		
		return ordenservice.save(ordmedinsAct);
	}

}
