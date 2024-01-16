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


import com.js.plataformasalud.modelos.entidades.TipoPaciente;
import com.js.plataformasalud.modelos.servicios.ITipoPacienteServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TipoPacienteRestController {
	
	private ITipoPacienteServiceImpl tipacserv;

	@GetMapping("/tipopaciente")
	public List<TipoPaciente> index(){
		return (List<TipoPaciente>) tipacserv.findAll();
	}
	
	@GetMapping("/tipopaciente/{idtipac}")
	public ResponseEntity<?> mostrar (@PathVariable Long idtipac){
		TipoPaciente tipac = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				tipac = tipacserv.findById(idtipac); 
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(tipac == null) {
		response.put("mensaje", "El tipo de paciente ID: ".concat(idtipac.toString().concat(" no existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<TipoPaciente>(tipac, HttpStatus.OK);
	}
	
	@PostMapping("/tipopaciente")
	public ResponseEntity<?> crear (@Valid @RequestBody TipoPaciente tipac, BindingResult validacion){
		TipoPaciente tpac = null;
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
			tpac = tipacserv.save(tipac);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El tipo de paciente ha sido creado con éxito!");
		response.put("tipac", tpac);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipopaciente/{idtipac}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody TipoPaciente tipac, @PathVariable Long idtipac, BindingResult validacion) {
		
		TipoPaciente tipacActual = tipacserv.findById(idtipac);
		
		TipoPaciente tipacUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(tipacActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el tipo de paciente ID: "
					.concat(idtipac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
				tipacActual.setDatecreatipac(tipac.getDatecreatipac());
				tipacActual.setNomtipac(tipac.getNomtipac());
				
				tipacUpdate = tipacserv.save(tipacActual);
				
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar el tipo de paciente en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "el tipo de paciente ha sido actualizada con éxito!");
		response.put("tipaciente", tipacUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
}
