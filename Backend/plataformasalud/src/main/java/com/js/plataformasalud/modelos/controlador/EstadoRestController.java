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

import com.js.plataformasalud.modelos.entidades.Estado;
import com.js.plataformasalud.modelos.servicios.IEstadoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class EstadoRestController {
	
	private IEstadoService statserv;
	
	@GetMapping("/estados")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Estado> index(){
		return statserv.findAll();
	}
	
	@GetMapping("/estados/{idstatus}")
	public ResponseEntity<?> mostrar (@PathVariable Long idstatus){
		Estado status = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				status = statserv.findById(idstatus); 
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(status == null) {
		response.put("mensaje", "El estado ID: ".concat(idstatus.toString().concat(" no existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<Estado>(status, HttpStatus.OK);
	
}
	@PostMapping("/estados")
	public ResponseEntity<?> crear (@Valid @RequestBody Estado status, BindingResult validacion){
		Estado estado = null;
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
			estado = statserv.save(status);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El estado ha sido creado con éxito!");
		response.put("estado", estado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/estados/{idstatus}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody Estado status, @PathVariable Long idstatus, BindingResult validacion) {
		
		Estado statusActual = statserv.findById(idstatus);
		
		Estado statusUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(statusActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el estado ID: "
					.concat(idstatus.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
				statusActual.setDatecreatstatus(status.getDatecreatstatus());
				statusActual.setNomstatus(status.getNomstatus());
				statusActual.setDetstatus(status.getDetstatus());
			
				statusUpdate = statserv.save(statusActual);
				
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar el estado en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "el estado ha sido actualizada con éxito!");
		response.put("Estados", statusUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
}
