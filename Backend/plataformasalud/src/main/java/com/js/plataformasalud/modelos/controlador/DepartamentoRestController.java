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

import com.js.plataformasalud.modelos.entidades.Departamento;
import com.js.plataformasalud.modelos.servicios.IDepartamentoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class DepartamentoRestController {
	
	private IDepartamentoServiceImpl depservice;

	@GetMapping("/departamentos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Departamento> index(){
		return depservice.findAll();
	}
	
	@GetMapping("/departamentos/{iddep}")
	public ResponseEntity<?> mostrar (@PathVariable Long iddep){
		Departamento departamento = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				departamento = depservice.findById(iddep);
			} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(departamento == null) {
			response.put("mensaje", "El departamento ID: ".concat(iddep.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/departamentos")
	public ResponseEntity<?> crear (@Valid @RequestBody Departamento departamento, BindingResult validacion){
		Departamento dep = null;
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
			dep = depservice.save(departamento);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El departamento ha sido creado con éxito!");
		response.put("departamento", dep);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
		
	@PutMapping("/departamentos/{iddep}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody Departamento departamento, @PathVariable Long iddep, BindingResult validacion) {
		
		Departamento depActual = depservice.findById(iddep);
		
		Departamento depUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(depActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el departamento ID: "
					.concat(iddep.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			depActual.setDatecreatdep(departamento.getDatecreatdep());
			depActual.setEstdep_fk(departamento.getEstdep_fk());
			depActual.setNomdep(departamento.getNomdep());
						
			depUpdate = depservice.save(depActual);
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar el departamento en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "El departamento ha sido actualizada con éxito!");
		response.put("departamento", depUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}


}
