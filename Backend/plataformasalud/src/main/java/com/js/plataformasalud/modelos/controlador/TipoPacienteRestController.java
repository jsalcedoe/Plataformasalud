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

import com.js.plataformasalud.modelos.entidades.TipoPaciente;
import com.js.plataformasalud.modelos.servicios.ITipoPacienteServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class TipoPacienteRestController {
	
	private ITipoPacienteServiceImpl tipacserv;

	public TipoPacienteRestController(ITipoPacienteServiceImpl tipacserv) {
		super();
		this.tipacserv = tipacserv;
	}
	
	@GetMapping("/tipopaciente")
	public List<TipoPaciente> index(){
		return (List<TipoPaciente>) tipacserv.findAll();
	}
	
	@GetMapping("/tipopaciente/{idtipac}")
	public TipoPaciente mostrar (@PathVariable long idtipac) {
		return tipacserv.findById(idtipac);
	}
	
	@PostMapping("/tipopaciente")
	public TipoPaciente crear(@RequestBody TipoPaciente tipac) {
		return tipacserv.save(tipac);
	}
	
	@PutMapping("/tipopaciente/{idtipac}")
	public TipoPaciente update (@PathVariable long idtipac,@RequestBody TipoPaciente tipac) {
		TipoPaciente tipacAct = tipacserv.findById(idtipac);
		
		tipacAct.setNomtipac(tipac.getNomtipac());
		
		return tipacserv.save(tipacAct);
	}
	
	
	
	

}
