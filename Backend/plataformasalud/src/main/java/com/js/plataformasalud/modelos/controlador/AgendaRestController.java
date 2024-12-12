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

import com.js.plataformasalud.modelos.entidades.Agenda;
import com.js.plataformasalud.modelos.servicios.IAgendaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class AgendaRestController {
	
	
	private IAgendaServiceImpl agmedserv;
	
	@GetMapping("/agenda")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Agenda> index(){
		return agmedserv.findAll();
	}
	
	@GetMapping("/agenda/{idagmed}")
	public ResponseEntity<?> mostrar(@PathVariable Long idagmed) {
		Agenda agmed = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			agmed = agmedserv.findById(idagmed);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (agmed == null) {
			response.put("mensaje", "La agenda ID: ".concat(idagmed.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Agenda>(agmed, HttpStatus.OK);
	}
	
	@PostMapping("/agenda")
	public ResponseEntity<?> save (@Valid @RequestBody Agenda agmed, BindingResult result) {
		Agenda Newagmed = null;
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
				Newagmed= agmedserv.save(agmed);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la agenda en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La agenda ha sido creado con éxito!");
		response.put("Agenda", Newagmed);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/agenda/{idagmed}")
	public ResponseEntity <?> actualizar (@PathVariable Long idagmed,@RequestBody Agenda agmed, BindingResult result) {
		
		Agenda agmedUpdate = null;
		Agenda agmedAct = agmedserv.findById(idagmed);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (agmedAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el cargo ID: "
					.concat(idagmed.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			agmedAct.setEsthab_fk(agmed.getEsthab_fk());
			agmedAct.setFechacreatagmed(agmed.getFechacreatagmed());
			agmedAct.setHorafinagmed(agmed.getHorafinagmed());
			agmedAct.setHorainicioagmed(agmed.getHorainicioagmed());
			agmedAct.setInteragmed(agmed.getInteragmed());
			agmedAct.setTotalagmed(agmed.getTotalagmed());
			agmedAct.setUbicaagmed_fk(agmed.getUbicaagmed_fk());
			agmedAct.setUseragmed_fk(agmed.getUseragmed_fk());
			
			agmedUpdate = agmedserv.save(agmedAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la agenda en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La agenda ha sido actualizado con éxito!");
		response.put("Agenda", agmedUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
