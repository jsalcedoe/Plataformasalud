package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.ProcedimientosExamenes;
import com.js.plataformasalud.modelos.servicios.IProcedimientoExamenesImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class ProcedimientoExamenesRestController {
	@Autowired
	private IProcedimientoExamenesImpl procexamservice;
	
	@GetMapping("/procexam")
	public List<ProcedimientosExamenes> index(){
		
		return procexamservice.findAll();
		
	}
	
	@GetMapping("/procexam/{codprocexam}")
	public ProcedimientosExamenes mostrar(@PathVariable String codprocexam) {
		return procexamservice.findById(codprocexam);
	}
	
	@PostMapping("/procexam")
	public ProcedimientosExamenes crear(@RequestBody ProcedimientosExamenes procexam) {
		return procexamservice.save(procexam);
	}
	
	@PutMapping("procexam/{codprocexam}")
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
