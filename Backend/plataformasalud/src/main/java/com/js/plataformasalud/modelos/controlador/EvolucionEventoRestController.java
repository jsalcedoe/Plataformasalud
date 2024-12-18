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
import com.js.plataformasalud.modelos.entidades.EvolucionEvento;
import com.js.plataformasalud.modelos.servicios.IEvolucionEventoServiceImpl;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class EvolucionEventoRestController {
	
	private IEvolucionEventoServiceImpl evoserv;
	
	
	@GetMapping("/evoluciones")
	@ResponseStatus(code = HttpStatus.OK)
	public List<EvolucionEvento> index(){
		return evoserv.findAll();
	}
	
	@GetMapping("evoluciones/{idevol}")
	public ResponseEntity<?> mostrar(@PathVariable Long idevol) {
		EvolucionEvento evol = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			evol = evoserv.findById(idevol);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (evol == null) {
			response.put("mensaje", "La evolucion con ID: ".concat(idevol.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EvolucionEvento>(evol, HttpStatus.OK);
	}
	
	@PostMapping("/evoluciones")
	public ResponseEntity<?> save (@Valid @RequestBody EvolucionEvento evol, BindingResult result) {
		EvolucionEvento Newevol = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
				Newevol= evoserv.save(evol);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la evolucion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La evolucion ha sido creada con éxito!");
		response.put("TipoNota", Newevol);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("evoluciones/{idevol}")
	public ResponseEntity <?> actualizar (@PathVariable Long idevol,@RequestBody EvolucionEvento evol, BindingResult result) {
		
		EvolucionEvento evolUpdate = null;
		EvolucionEvento evolAct = evoserv.findById(idevol);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (evolAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la evolucion ID: "
					.concat(idevol.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			evolAct.setDatecreatevol(evol.getDatecreatevol());
			evolAct.setDateditevol(evol.getDateditevol());
			evolAct.setDetevol(evol.getDetevol());
			evolAct.setEventevo_fk(evol.getEventevo_fk());
			evolAct.setNotaevol_fk(evol.getNotaevol_fk());
			
			
			evolUpdate = evoserv.save(evolAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la evolucion en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La evolucion ha sido actualizada con éxito!");
		response.put("EvolucionEvento", evolUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
