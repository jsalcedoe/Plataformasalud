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
	public ResponseEntity<?> mostrar (@PathVariable Long idagmed){
		Agenda agenda = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				agenda = agmedserv.findById(idagmed);
			} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(agenda == null) {
			response.put("mensaje", "La agenda ID: ".concat(idagmed.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Agenda>(agenda, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/agenda")
	public ResponseEntity<?> crear (@Valid @RequestBody Agenda agmed, BindingResult validacion){
		Agenda agenda = null;
		Map<String, Object> response = new HashMap<>();
		
		if(validacion.hasErrors()) {

			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			agenda = agmedserv.save(agenda);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La agenda ha sido creada con éxito!");
		response.put("agenda", agenda);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
		
	@PutMapping("/agenda/{idagmed}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody Agenda agmed, @PathVariable Long idagmed, BindingResult validacion) {
		
		Agenda AgendaActual = agmedserv.findById(idagmed);
		
		Agenda agendaUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(AgendaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la agenda ID: "
					.concat(idagmed.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			AgendaActual.setConsagmed(agmed.getConsagmed());
			AgendaActual.setCupscita(agmed.getCupscita());
			AgendaActual.setFechaagmed(agmed.getFechaagmed());
			AgendaActual.setHorafinagmed(agmed.getHorafinagmed());
			AgendaActual.setHorainiagmed(agmed.getHorainiagmed());
			AgendaActual.setIntervtempagmed(agmed.getIntervtempagmed());
			AgendaActual.setMediagmed(agmed.getMediagmed());
			AgendaActual.setTotalcitasagmed(agmed.getTotalcitasagmed());
			
			agendaUpdate = agmedserv.save(AgendaActual);
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar la agenda en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "La agenda ha sido actualizada con éxito!");
		response.put("agenda", agendaUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}

	
	

}
