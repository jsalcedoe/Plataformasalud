package com.js.plataformasalud.modelos.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.Consultorio;
import com.js.plataformasalud.modelos.servicios.IConsultorioServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ConsultorioRestController {
	private IConsultorioServiceImpl consultorioService;

	@GetMapping("/consultorio")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Consultorio> index(){
		return consultorioService.findAll();
		
	}
	
	@GetMapping("/consultorio/{idconsultorio}")
	public ResponseEntity<?> mostrar(@PathVariable Long idconsultorio){
		Consultorio cons = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cons = consultorioService.FindById(idconsultorio);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cons == null) {
			response.put("mensaje", "La ciudad ID: ".concat(idconsultorio.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Consultorio>(cons, HttpStatus.OK);
	}
	
	@PostMapping("/consultorio")
	public ResponseEntity<?> crear(@Valid @RequestBody Consultorio consultorio, BindingResult result){
		Consultorio cons = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			cons = consultorioService.save(consultorio);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El consultorio ha sido creado con éxito!");
		response.put("consultorio", cons );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/consultorio/{idconsultorio}")
	public ResponseEntity<?> update (@Valid @RequestBody Consultorio consultorio, @PathVariable Long idconsultorio, BindingResult result) {
		
		Consultorio consActual = consultorioService.FindById(idconsultorio);
		Consultorio cons = null;
		
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (consActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el consultorio ID: "
					.concat(idconsultorio.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			consActual.setEstado(consultorio.getEstado());
			consActual.setNomconsul(consultorio.getNomconsul());
			
			cons = consultorioService.save(consActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el consultorio en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El consultorio ha sido actualizado con éxito!");
		response.put("consultorio",cons);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
		}
		
	}

