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

import com.js.plataformasalud.modelos.entidades.Contrato;
import com.js.plataformasalud.modelos.servicios.IContratoServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ContratoRestController {
	
	private IContratoServiceImpl contratoserv;
	
	@GetMapping("/contratos")
	public List <Contrato> index(){
		return (List <Contrato>) contratoserv.findAll();
	}
	
	@GetMapping("/contratos/idcontrato")
	public Contrato mostrar(@PathVariable Long idcontrato) {
		return contratoserv.findById(idcontrato);
	}
	
	@PostMapping("/contratos")
	public Contrato crear (@RequestBody Contrato contrato) {
		return contratoserv.save(contrato);
	}
	
	@PutMapping("/contratos/{idcontrato}")
	public Contrato update(@PathVariable Long idcontrato,@RequestBody Contrato contrato) {
		
		Contrato contAct = contratoserv.findById(idcontrato);
		contAct.setDetcontrato(contrato.getDetcontrato());
		contAct.setEntidad(contrato.getEntidad());
		
		return contratoserv.save(contAct);
	}

}
