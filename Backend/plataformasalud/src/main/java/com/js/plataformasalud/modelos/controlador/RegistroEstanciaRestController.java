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
import com.js.plataformasalud.modelos.entidades.RegistroEstancia;
import com.js.plataformasalud.modelos.servicios.IRegistroEstanciaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class RegistroEstanciaRestController {
	
	private IRegistroEstanciaServiceImpl regestserv;
	
	@GetMapping("/registroestancias")
	@ResponseStatus(code = HttpStatus.OK)
	public List<RegistroEstancia> index(){
		return regestserv.findAll();
	}
	
	@GetMapping("registroestancias/{idregest}")
	public ResponseEntity<?> mostrar(@PathVariable Long idregest) {
		RegistroEstancia regest = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			regest = regestserv.findById(idregest);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (regest == null) {
			response.put("mensaje", "El registro de estancia ID: ".concat(idregest.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RegistroEstancia>(regest, HttpStatus.OK);
	}
	
	@PostMapping("/registroestancias")
	public ResponseEntity<?> save (@Valid @RequestBody RegistroEstancia regest, BindingResult result) {
		RegistroEstancia Newregest = null;
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
				Newregest = regestserv.save(regest);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la estancia en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El registro de estancia ha sido creado con éxito!");
		response.put("RegistroEstancia", Newregest);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("registroestancias/{idregest}")
	public ResponseEntity <?> actualizar (@PathVariable Long idregest,@RequestBody RegistroEstancia regest, BindingResult result) {
		
		RegistroEstancia regestUpdate = null;
		RegistroEstancia regestAct = regestserv.findById(idregest);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (regestAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el registro de estancia ID: "
					.concat(idregest.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			regestAct.setEstregest_fk(regest.getEstregest_fk());
			regestAct.setEventregest_fk(regest.getEventregest_fk());
			regestAct.setFechafin(regest.getFechafin());
			regestAct.setFechaini(regest.getFechaini());
			regestAct.setRegcama_fk(regest.getRegcama_fk());
			
			regestUpdate = regestserv.save(regestAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el registro de estancia en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El registro de estancia ha sido actualizado con éxito!");
		response.put("RegistroEstancia", regestUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
