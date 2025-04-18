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
import com.js.plataformasalud.modelos.entidades.Evento;
import com.js.plataformasalud.modelos.servicios.IEventoServiceImpl;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class EventoRestController {
	
	private IEventoServiceImpl eventserv;
	
	@GetMapping("/evento")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Evento> index(){
		return eventserv.findAll();
	}
	
	@GetMapping("evento/{idevent}")
	public ResponseEntity<?> mostrar(@PathVariable Long idevent) {
		Evento event = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			event = eventserv.findById(idevent);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (event == null) {
			response.put("mensaje", "El evento ID: ".concat(idevent.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Evento>(event, HttpStatus.OK);
	}
	
	/*@GetMapping("/eventos/contar/{numDocPac}")
    public ResponseEntity<Integer> contarEventosPorNumDocPac(@PathVariable String numDocPac) {
        int count = eventserv.countEventosByNumDocPac(numDocPac);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }*/

	
	@PostMapping("/evento")
	public ResponseEntity<?> save (@Valid @RequestBody Evento event, BindingResult result) {
		Evento Newevent = null;
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
			Newevent= eventserv.save(event);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el evento en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El evento ha sido creado con éxito!");
		response.put("cargo", Newevent);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("evento/{idevent}")
	public ResponseEntity <?> actualizar (@PathVariable Long idevent,@RequestBody Evento event, BindingResult result) {
		
		Evento eventUpdate = null;
		Evento eventAct = eventserv.findById(idevent);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (eventAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el evento ID: "
					.concat(idevent.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			eventAct.setEstevent_fk(event.getEstevent_fk());
			eventAct.setFechafinevent(event.getFechafinevent());
			eventAct.setFechainievent(event.getFechainievent());
			eventAct.setPacevent_fk(event.getPacevent_fk());
			
			
			eventUpdate = eventserv.save(eventAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el event en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El evento ha sido actualizado con éxito!");
		response.put("cargo", eventUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
