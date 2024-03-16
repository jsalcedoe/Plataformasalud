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

import com.js.plataformasalud.modelos.entidades.Ciudad;
import com.js.plataformasalud.modelos.servicios.ICiudadServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class CiudadRestController {
	
	private ICiudadServiceImpl ciuservice;

	@GetMapping("/ciudades")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Ciudad> index(){
		return ciuservice.findAll();
	}
	
	@GetMapping("/ciudades/{idciu}")
	public ResponseEntity<?> mostrar(@PathVariable Long idciu) {
		Ciudad ciudad = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			ciudad = ciuservice.FindById(idciu);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (ciudad == null) {
			response.put("mensaje", "La ciudad ID: ".concat(idciu.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Ciudad>(ciudad, HttpStatus.OK);
	}
	
	@PostMapping("/ciudades")
	public ResponseEntity<?> save (@Valid @RequestBody Ciudad ciudad, BindingResult result) {
		Ciudad NuevaCiudad = null;
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
				NuevaCiudad = ciuservice.save(ciudad);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la ciudad en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La ciudad ha sido creado con éxito!");
		response.put("ciudad", NuevaCiudad);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/ciudades/{idciu}")
	public ResponseEntity <?> actualizar (@PathVariable Long idciu,@RequestBody Ciudad ciudad, BindingResult result) {
		
		Ciudad ciudadUpdate = null;
		Ciudad ciuAct = ciuservice.FindById(idciu);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (ciuAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la ciudad ID: "
					.concat(idciu.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			ciuAct.setDatecreatciu(ciudad.getDatecreatciu());
			ciuAct.setDepciu_fk(ciudad.getDepciu_fk());
			ciuAct.setNomciu(ciudad.getNomciu());
			ciuAct.setEstciu_fk(ciudad.getEstciu_fk());
			
			ciudadUpdate =ciuservice.save(ciuAct);
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la ciudad en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La ciudad ha sido actualizada con éxito!");
		response.put("ciudad", ciudadUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
