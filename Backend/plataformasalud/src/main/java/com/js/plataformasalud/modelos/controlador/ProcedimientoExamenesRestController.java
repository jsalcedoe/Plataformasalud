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

import com.js.plataformasalud.modelos.entidades.ProcedimientosExamenes;
import com.js.plataformasalud.modelos.servicios.IProcedimientoExamenesImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ProcedimientoExamenesRestController {
	
	private IProcedimientoExamenesImpl procexamservice;
	
	@GetMapping("/procexam")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProcedimientosExamenes> index(){
		
		return procexamservice.findAll();
		
	}
	
	@GetMapping("/procexam/{codprocexam}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProcedimientosExamenes mostrar(@PathVariable String codprocexam) {
		return procexamservice.findById(codprocexam);
	}
	
	@PostMapping("/procexam")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProcedimientosExamenes crear(@RequestBody ProcedimientosExamenes procexam) {
		return procexamservice.save(procexam);
	}
	
	@PutMapping("procexam/{codprocexam}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProcedimientosExamenes update (@PathVariable String codprocexam, @RequestBody ProcedimientosExamenes procexam) {
		
		ProcedimientosExamenes procexamAct = procexamservice.findById(codprocexam);
		
		procexamAct.setEstado(procexam.getEstado());
		procexamAct.setNomprocexam(procexam.getNomprocexam());
		procexamAct.setPrecio(procexam.getPrecio());
		procexamAct.setSexo(procexam.getSexo());
		procexamAct.setTprocexam(procexam.getTprocexam());
		procexamAct.setTarifprocexam(procexam.getTarifprocexam());
		
		return procexamservice.save(procexamAct);
	}

}
