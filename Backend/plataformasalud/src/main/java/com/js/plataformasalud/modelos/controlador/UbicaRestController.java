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

import com.js.plataformasalud.modelos.entidades.Ubicacion;
import com.js.plataformasalud.modelos.servicios.IUbicaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class UbicaRestController {
	
	private IUbicaServiceImpl ubicaservice;
	
	@GetMapping("/ubicaciones")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Ubicacion> index(){
		return ubicaservice.findAll();
	}
	
	@GetMapping("/ubicaciones/{idubica}")
	public ResponseEntity<?> mostrar(@PathVariable Long idubica) {
		
			Ubicacion ubica = null;
			Map<String, Object> response = new HashMap<>();
		
			try {
				ubica = ubicaservice.findById(idubica);
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al realizar la consulta en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if (ubica == null) {
				response.put("mensaje", "La ubicación ID: ".concat(idubica.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Ubicacion>(ubica, HttpStatus.OK);
		}
	
	
	@PostMapping("/ubicaciones")
	public ResponseEntity<?> save (@Valid @RequestBody Ubicacion ubica, BindingResult result) {
		Ubicacion Newubica = null;
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
				Newubica= ubicaservice.save(ubica);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la ubicacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La ubicacion ha sido creada con éxito!");
		response.put("Ubicacion", Newubica);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/ubicaciones/{idubica}")
	public ResponseEntity <?> actualizar (@PathVariable Long idubica,@RequestBody Ubicacion ubicacion, BindingResult result) {
		
		Ubicacion ubicaUpdate = null;
		Ubicacion ubicaAct = ubicaservice.findById(idubica);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (ubicaAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la ubicación ID: "
					.concat(idubica.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			ubicaAct.setDatecreatubic(ubicacion.getDatecreatubic());
			ubicaAct.setDetubica(ubicacion.getDetubica());
			ubicaAct.setEstubica_fk(ubicacion.getEstubica_fk());
			ubicaAct.setNomubicaciones(ubicacion.getNomubicaciones());		
			
			ubicaUpdate = ubicaservice.save(ubicaAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la ubicación en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La ubicación ha sido actualizado con éxito!");
		response.put("Ubicacion", ubicaUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
