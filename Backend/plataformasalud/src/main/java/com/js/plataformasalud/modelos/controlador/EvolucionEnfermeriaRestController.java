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
import com.js.plataformasalud.modelos.entidades.EvolucionEnfermeria;
import com.js.plataformasalud.modelos.servicios.IEvolucionEnfermeriaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class EvolucionEnfermeriaRestController {
	
	private IEvolucionEnfermeriaServiceImpl evoenfserv;
	
	@GetMapping("/evolucionenfermeria")
	@ResponseStatus(code = HttpStatus.OK)
	public List<EvolucionEnfermeria> index(){
		return evoenfserv.findAll();
	}
	
	
	@GetMapping("/evolucionenfermeria/{idevoenf}")
	public ResponseEntity<?> mostrar (@PathVariable Long idevoenf){
		EvolucionEnfermeria evoenf = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				evoenf = evoenfserv.FindById(idevoenf);
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(evoenf == null) {
		response.put("mensaje", "La evolución ID: ".concat(idevoenf.toString().concat(" no existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<EvolucionEnfermeria>(evoenf, HttpStatus.OK);
	
}
	
	@PostMapping("/evolucionenfermeria")
	public ResponseEntity<?> crear (@Valid @RequestBody EvolucionEnfermeria evoenf, BindingResult validacion){
		EvolucionEnfermeria evoenfermeria = null;
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
			evoenfermeria = evoenfserv.save(evoenf);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La evolución ha sido creada con éxito!");
		response.put("EvolucionEnfermeria", evoenfermeria);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/evolucionenfermeria/{idevoenf}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody EvolucionEnfermeria evoenf, @PathVariable Long idevoenf, BindingResult validacion) {
		
		EvolucionEnfermeria evoenfActual = evoenfserv.FindById(idevoenf);
		
		EvolucionEnfermeria evoenfUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(evoenfActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la evolucion de enfermeria ID: "
					.concat(idevoenf.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
				evoenfActual.setDatecreatevoenf(evoenf.getDatecreatevoenf());
				evoenfActual.setDateditevoenf(evoenf.getDateditevoenf());
				evoenfActual.setDetevoenf(evoenf.getDetevoenf());
				evoenfActual.setEstevoenf_fk(evoenf.getEstevoenf_fk());
				evoenfActual.setEventevoenf_fk(evoenf.getEventevoenf_fk());
				
				evoenfUpdate = evoenfserv.save(evoenfActual);
								
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar la evolución en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "La evolución de enfermeria ha sido actualizada con éxito!");
		response.put("EvolucionEnfermeria", evoenfUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	

}
