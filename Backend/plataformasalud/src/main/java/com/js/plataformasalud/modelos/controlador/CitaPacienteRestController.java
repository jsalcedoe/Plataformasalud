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

import com.js.plataformasalud.modelos.entidades.CitaPaciente;
import com.js.plataformasalud.modelos.servicios.ICitaPacienteServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class CitaPacienteRestController {
	
	private ICitaPacienteServiceImpl citas;
	
	@GetMapping("/citapaciente")
	public List<CitaPaciente> index(){
		return citas.findAll();
	}
	
	@GetMapping("/citapaciente/{idcitpac}")
	public ResponseEntity<?> mostrar (@PathVariable Long idcitpac){
		
		CitaPaciente citapac = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
				citapac = citas.findById(idcitpac);
			} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(citapac == null) {
			response.put("mensaje", "La cita ID: ".concat(idcitpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CitaPaciente>(citapac, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/citapaciente")
	public ResponseEntity<?> crear (@Valid @RequestBody CitaPaciente citapac, BindingResult validacion){
		
		CitaPaciente citpac = null;
		
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
			citpac = citas.save(citpac);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La cita ha sido creada con éxito!");
		response.put("citapac", citpac);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
		
	@PutMapping("/citapaciente/{idcitpac}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody CitaPaciente citapac, @PathVariable Long idcitpac, BindingResult validacion) {
		
		CitaPaciente citapacActual = citas.findById(idcitpac);
		
		CitaPaciente citapacUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(citapacActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la cita ID: "
					.concat(idcitpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			citapacActual.setDetnovcitpac(citapac.getDetnovcitpac());
			citapacActual.setIdagendacit(citapac.getIdagendacit());
			citapacActual.setMotcitpac(citapac.getMotcitpac());
			citapacActual.setNovcitpac(citapac.getNovcitpac());
			citapacActual.setNumdocpac(citapac.getNumdocpac());
					
			citapacUpdate = citas.save(citapacActual);
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar la cita medica en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "La cita ha sido actualizada con éxito!");
		response.put("citapac", citapacUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	

}
