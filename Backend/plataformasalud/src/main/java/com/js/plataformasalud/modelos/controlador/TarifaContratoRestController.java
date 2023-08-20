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

import com.js.plataformasalud.modelos.entidades.TarifaContrato;
import com.js.plataformasalud.modelos.servicios.ITarifaContratoServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TarifaContratoRestController {
	
	private ITarifaContratoServiceImpl tarifaservice;
	
	@GetMapping("/tarifascontratos")
	public List<TarifaContrato> index(){
		return tarifaservice.findAll();
	}
	
	@GetMapping("/tarifascontratos/{idtarifcont}")
	public TarifaContrato mostrar(@PathVariable Long idtarifcont) {
		return tarifaservice.findById(idtarifcont);
	}
	
	@PostMapping("/tarifascontratos")
	public TarifaContrato crear (@RequestBody TarifaContrato taricont) {
		return tarifaservice.save(taricont);
	}
	
	@PutMapping("/tarifascontratos/{idtarifcont}")
	public TarifaContrato update (@PathVariable Long idtarifcont,@RequestBody TarifaContrato taricont) {
		
		TarifaContrato tarifAct = tarifaservice.findById(idtarifcont);
		
		tarifAct.setContarif(taricont.getContarif());
		tarifAct.setDetarifcont(taricont.getDetarifcont());
		tarifAct.setMedins(taricont.getMedins());
		tarifAct.setProcexam(taricont.getProcexam());
		tarifAct.setTarifacont(taricont.getTarifacont());
		
		return tarifaservice.save(tarifAct);
	}

}
