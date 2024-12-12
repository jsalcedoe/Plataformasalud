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

import com.js.plataformasalud.modelos.entidades.OrdenesProcExam;
import com.js.plataformasalud.modelos.servicios.IOrdenesProcExamServicesImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class OrdenesProcExamRestController {
	
	
	private IOrdenesProcExamServicesImpl ordenservice;
	
	@GetMapping("/ordenes")
	@ResponseStatus(code = HttpStatus.OK)
	public List<OrdenesProcExam> index(){
		return (List<OrdenesProcExam>)ordenservice.findAll();
	}
	
	@GetMapping("/ordenes/{idordprocexam}")
	@ResponseStatus(code = HttpStatus.OK)
	public OrdenesProcExam mostrar(@PathVariable Long idordprocexam) {
		return ordenservice.findById(idordprocexam);
	}
	
	@PostMapping("/ordenes")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrdenesProcExam crear(@RequestBody OrdenesProcExam ordenes) {
		return ordenservice.save(ordenes);
	}
	
	@PutMapping("/ordenes/{idordprocexam}")
	@ResponseStatus(code = HttpStatus.OK)
	public OrdenesProcExam editar(@RequestBody OrdenesProcExam ordenes, @PathVariable Long idordprocexam) {
		OrdenesProcExam ordenesActual = ordenservice.findById(idordprocexam);
		ordenesActual.setCantprocexam(ordenes.getCantprocexam());
		ordenesActual.setFechaordprocexam(ordenes.getFechaordprocexam());
		ordenesActual.setObsprocexam(ordenes.getObsprocexam());
		ordenesActual.setProcexamord(ordenes.getProcexamord());
		ordenesActual.setEventprocexamord_fk(ordenes.getEventprocexamord_fk());
		ordenesActual.setFechaeditordprocexam(ordenes.getFechaeditordprocexam());
		ordenesActual.setTprocexamord_fk(ordenes.getTprocexamord_fk());
		
		return ordenservice.save(ordenesActual);
	}

}
