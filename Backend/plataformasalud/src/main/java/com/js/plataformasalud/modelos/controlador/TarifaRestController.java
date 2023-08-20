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

import com.js.plataformasalud.modelos.entidades.Tarifa;
import com.js.plataformasalud.modelos.servicios.ITarifaServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class TarifaRestController {
	
	private ITarifaServiceImpl tarifaService;

	public TarifaRestController(ITarifaServiceImpl tarifaService) {
		super();
		this.tarifaService = tarifaService;
	}
	
	@GetMapping("/tarifas")
	public List<Tarifa> index(){
		return tarifaService.findAll();
	}
	
	@GetMapping("/tarifas/{idtarifa}")
	public Tarifa mostrar (@PathVariable Long idtarifa) {
		
		return tarifaService.findById(idtarifa);
	}
	
	@PostMapping("/tarifas")
	public Tarifa crear (@RequestBody Tarifa tarifa) {
		return tarifaService.save(tarifa);
	}
	
	@PutMapping("/tarifas/{idtarifa}")
	public Tarifa editar (@PathVariable Long idtarifa, @RequestBody Tarifa tarifa) {
		Tarifa tarifaAct = tarifaService.findById(idtarifa);
		
		tarifaAct.setDetatarifa(tarifa.getDetatarifa());
		tarifaAct.setNomtarifa(tarifa.getNomtarifa());
		tarifaAct.setValortarifa(tarifa.getValortarifa());
		
		return tarifaService.save(tarifaAct);
	}

}
