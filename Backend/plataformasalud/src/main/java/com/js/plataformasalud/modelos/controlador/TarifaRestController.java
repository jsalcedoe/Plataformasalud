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

import com.js.plataformasalud.modelos.entidades.Tarifa;
import com.js.plataformasalud.modelos.servicios.ITarifaServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TarifaRestController {
	
	private ITarifaServiceImpl tarifaService;

		
	@GetMapping("/tarifas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Tarifa> index(){
		return tarifaService.findAll();
	}
	
	@GetMapping("/tarifas/{idtarifa}")
	@ResponseStatus(code = HttpStatus.OK)
	public Tarifa mostrar (@PathVariable Long idtarifa) {
		
		return tarifaService.findById(idtarifa);
	}
	
	@PostMapping("/tarifas")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Tarifa crear (@RequestBody Tarifa tarifa) {
		return tarifaService.save(tarifa);
	}
	
	@PutMapping("/tarifas/{idtarifa}")
	@ResponseStatus(code = HttpStatus.OK)
	public Tarifa editar (@PathVariable Long idtarifa, @RequestBody Tarifa tarifa) {
		Tarifa tarifaAct = tarifaService.findById(idtarifa);
		
		tarifaAct.setDetatarifa(tarifa.getDetatarifa());
		tarifaAct.setNomtarifa(tarifa.getNomtarifa());
		tarifaAct.setValortarifa(tarifa.getValortarifa());
		
		return tarifaService.save(tarifaAct);
	}

}
